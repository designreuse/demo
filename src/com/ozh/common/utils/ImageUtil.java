package com.ozh.common.utils;

import com.sun.imageio.plugins.jpeg.JPEGImageWriter;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.media.jai.Interpolation;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by ozh on 2016/4/15.
 */
public class ImageUtil {

    public static final  String IMAGE_TYPE_BMP = "BMP";
    public static final String IMAGE_TYPE_GIF = "GIF";
    public static final String IMAGE_TYPE_JPEG = "JPEG";
    public static final String IMAGE_TYPE_JPG = "JPG";
    public static final String IMAGE_TYPE_PNG = "PNG";
    public static final String IMAGE_TYPE_PNM = "PNM";
    public static final String IMAGE_TYPE_TIFF = "TIFF";
    public static final String ERROR_MSG_SOURCE_IMAGE_FILE_NO_EXIST = "source Image File not exist!";

    static {
        System.setProperty("com.sun.media.jai.disableMediaLib", "true");
    }

    private ImageUtil(){}
    /**
     * 改变图片大小
     *
     *@param img Image 源图片
     * @param width int 宽
     * @param height int 高
     * @throws Exception
     * @return BufferedImage
     */
    private static Image changSize(Image img, int width, int height) {
        int w = img.getWidth(null);
        int h = img.getHeight(null);

//        if (w > width || h > height){
        if (w == h) {
            //宽度和高度，以小的为标准
            if (width >= height) {
                h = height;
                w = h;
            } else {
                w = width;
                h = w;
            }
        } else if (w > h) {
            float scale = (float) h / w;  //计得小于1的小数
            w = width;
            h = (int) (w * scale);       //按比例计算
        } else {
            float scale = (float) w / h;
            h = height;
            w = (int) (h * scale);
        }
//        }

        img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return img;
    }

    /**
     * 得到图片的缓冲图像
     *
     *@param img Image 源图片
     * @return BufferedImage
     */
    private static BufferedImage toBufferedImage(Image img){
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setBackground(Color.WHITE);
        g.fillRect(0, 0, w, h);

        bi.getGraphics().drawImage(img, 0, 0, w, h, null);
        return bi;
    }
    /*
    *
    * 用于头像图片定位
    * */

    private static BufferedImage toBufferedImage(Image img,int x,int y){
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, w, h);
        bi.getGraphics().drawImage(img, x, y, null);
        return bi;
    }

    public static BufferedImage toCroppedImage(File file,int x,int y,int w,int h) throws IOException {
        BufferedImage outImage= ImageIO.read(file);
        BufferedImage cropped=outImage.getSubimage(x, y, w, h);
        return cropped;

    }

    /**
     * 处理jpeg格式的图片  把调整好大小的图片进行转换并输出
     *
     * @param bi BufferedImage 源文件 的 图像数据
     * @param dest File 目的文件
     * @throws Exception
     */
    private static void toJPEG(BufferedImage bi, File dest) throws IOException {
        JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpeg").next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(dest);
        imageWriter.setOutput(ios);

        JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
/*
*        setCompressionMode:
*        指定是否执行压缩，如果执行压缩，将如何确定 compression 参数。mode 参数必须是四种模式之一，对它们的解释如下：
*        MODE_DISABLED - 如果该模式被设置为 MODE_DISABLED，则查询或修改压缩类型或参数的方法将会抛出 IllegalStateException（如果插件通常支持压缩）。
*        一些 writer（比如 JPEG）通常不提供未压缩的输出。在这种情况下，试图将模式设置为 MODE_DISABLED 将会抛出 UnsupportedOperationException 并且将不更改该模式。
*        MODE_EXPLICIT - 使用此 ImageWriteParam 中指定的压缩类型和质量设置进行压缩。任何以前设置的 compression 参数都将被丢弃。
*        MODE_COPY_FROM_METADATA - 使用传入 writer 的元数据对象中指定的 compression 参数。
*        MODE_DEFAULT - 使用默认 compression 参数。
*        默认值为 MODE_COPY_FROM_METADATA。
*/
        jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
/*
*        setCompressionQuality :
*        将压缩质量设置为 0 和 1 之间的某个值。默认情况下，只支持一种压缩质量设置；writer 可以提供扩展的 ImageWriteParam，其提供了更多控制。
*        对于有损失的压缩方案，压缩质量应该控制文件大小与图像质量之间的权衡（例如，通过在写入 JPEG 图像时选择量化表）。
*        对于无损失方案，可以使用压缩质量控制文件大小与执行压缩所用时间之间的权衡（例如，通过在写入 PNG 图像时优化行过滤器并设置 ZLIB 压缩级别）。
*       压缩质量为 0.0 通常被解释为“高度压缩很重要”，而该设置为 1.0 通常被解释为“高图像质量很重要”。
*/

        // jpegParams.setCompressionQuality(0.95f);
        jpegParams.setCompressionQuality(1.00f);
        IIOMetadata data = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(bi), jpegParams);

        imageWriter.write(data, new IIOImage(bi, null, null), jpegParams);
        ios.close();
        imageWriter.dispose();
    }

    /**
     * 处理gif格式的图片  把调整好大小的图片进行转换并输出 // gif to 静态的图片
     *
     * @param bi BufferedImage 源文件 的 图像数据
     * @param dest File 目的文件
     * @throws Exception
     */
//    private void toStaticGIF(BufferedImage bi, File dest) throws Exception{
//        AnimatedGifEncoder age = new AnimatedGifEncoder();
//        age.setQuality(1);
//        age.start(dest.toString());
//        age.addFrame(bi);
//        age.finish();
//    }

    /**
     * 处理gif格式的图片  把调整好大小的图片进行转换并输出 // gif to 动态的图片
     *
     * @param src File 源文件
     * @param dest File 目的文件
     * @param width int 宽
     * @param height int 高
     * @throws Exception
     */
    private static void toActiveGIF(File src, File dest, int width, int height) {
        //把gif图片 按帧拆分成jpg图片
        GifDecoder decoder = new GifDecoder();
        decoder.read(src.getPath());
        int n = decoder.getFrameCount(); //得到frame的个数
        ArrayList<BufferedImage> biList = new ArrayList<BufferedImage>();
        ArrayList<Integer> delayList = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            BufferedImage bi = decoder.getFrame(i); //得到帧
            //调整大小
            Image  img = changSize(bi, width, height);
            bi = toBufferedImage(img);
            biList.add(bi);
            int delay = decoder.getDelay(i); //得到延迟时间
            delayList.add(delay);
        }

        //多张jpg图合成gif动画
        AnimatedGifEncoder e = new AnimatedGifEncoder();
        e.setRepeat(0);
        e.start(dest.getPath());
        for (int i = 0; i < biList.size(); i++) {
            e.setDelay(delayList.get(i)); //设置播放的延迟时间
            e.addFrame(biList.get(i));  //添加到帧中
        }
        e.finish();
    }

    /**
     * 处理png格式的图片  把调整好大小的图片进行转换并输出
     *
     //* @param bi BufferedImage 源文件 的 图像数据
     * @param dest File 目的文件
     * @throws Exception
     */
    private static void toPNG(File src, File dest, int width, int height) throws IOException {
        Image  img = changSize(ImageIO.read(src), width, height);
        PlanarImage pi = loadImage(src.getPath(),img.getWidth(null),img.getHeight(null));
        FileOutputStream fout = new FileOutputStream(dest.getPath());
        encodeImage(pi, fout);
    }

    /* Load the source image. */
    private static PlanarImage loadImage(String imageName,int width, int height) throws IOException {
        /**
         * Create an input stream from the specified file name to be used with
         * the file decoding operator.
         */
        FileSeekableStream stream = new FileSeekableStream(imageName);

        /* Create an operator to decode the image file. */
        RenderedOp image1 = JAI.create("stream", stream);
        int sourceImageWidth = image1.getWidth();
        int sourceImageHeight = image1.getHeight();

        /**
         * Create a standard bilinear interpolation object to be used with the
         * "scale" operator.
         */

        Interpolation interpolation = Interpolation.getInstance(Interpolation.INTERP_NEAREST); // Interpolation.INTERP_BILINEAR
        /**
         * Stores the required input source and parameters in a ParameterBlock
         * to be sent to the operation registry, and eventually to the "scale"
         * operator.
         */
        ParameterBlock params = new ParameterBlock();
        params.addSource(image1);
        float xscale = (float) width / sourceImageWidth;
        float yscale = (float) height / sourceImageHeight;

        params.add(new Float(xscale));
        params.add(new Float(yscale));
        params.add(new Float(0.0f));
        params.add(new Float(0.0f));
        params.add(interpolation);
        /* Create an operator to scale image1. */
        PlanarImage src = JAI.create("scale", params);
        return src;
    }

    // Create the image encoder.
    private static void encodeImage(PlanarImage img, FileOutputStream out) throws IOException {
        ImageEncoder encoder = ImageCodec.createImageEncoder("png", out, null);
        encoder.encode(img);
        out.close();
    }


    /**
     * 处理其他格式的图片  把调整好大小的图片进行转换并输出
     *
     * @param bi BufferedImage 源文件 的 图像数据
     * @param dest File 目的文件
     * @throws Exception
     */
    private static void toOther(String imageType, BufferedImage bi, File dest) throws IOException {
        ImageIO.write(bi, imageType, dest);
    }

    /**
     * 改变图片大小
     *
     * @param imageType 图片类型,支持的图片类型在常量里定义
     * @param src File 源文件
     * @param dest File 目的文件
     * @param width int 宽
     * @param height int 高
     * @throws Exception
     */
    public static boolean doChangeSize(String imageType, File src, File dest, int width, int height) throws IOException {
        AssertExt.hasLength(imageType);
        if(src == null || !src.exists()){
            return false;
        }
        AssertExt.notNull(dest);
        AssertExt.isTrue(width > 0 && height > 0);
        AssertExt.hasLength(imageType);
        //处理gif图片
        if(imageType.equalsIgnoreCase(IMAGE_TYPE_GIF)){
//            toStaticGIF(bi, dest);
            toActiveGIF(src, dest, width, height);
            return true;
        }

        //处理其他图片
        Image img = ImageIO.read(src);
        img = changSize(img, width, height);
        BufferedImage bi = toBufferedImage(img);
        if (imageType.equalsIgnoreCase(IMAGE_TYPE_JPEG) || imageType.equalsIgnoreCase(IMAGE_TYPE_JPG)) {
            toJPEG(bi, dest);
            return true;
        }
        if(imageType.equalsIgnoreCase(IMAGE_TYPE_PNG)){
            toPNG(src, dest, width, height);
            return true;
        }
        toOther(imageType, bi, dest);
        return true;
    }


    public static boolean doChangeSize(String imageType, File src, File dest, int width, int height,int x,int y) throws IOException {
        AssertExt.hasLength(imageType);
        if(src == null || !src.exists()){
            return false;
        }
        AssertExt.notNull(dest);
        AssertExt.isTrue(width > 0 && height > 0);
        AssertExt.hasLength(imageType);
        //处理gif图片
        if(imageType.equalsIgnoreCase(IMAGE_TYPE_GIF)){
//            toStaticGIF(bi, dest);
            toActiveGIF(src, dest, width, height);
            return true;
        }

        //处理其他图片
        Image img = ImageIO.read(src);
        // img = changSize(img, width, height);
        BufferedImage bi = toCroppedImage(src,x,y,width,height);
        if (imageType.equalsIgnoreCase(IMAGE_TYPE_JPEG)) {
            toJPEG(bi, dest);
            return true;
        }
        if(imageType.equalsIgnoreCase(IMAGE_TYPE_PNG)){
            toPNG(src, dest, width, height);
            return true;
        }
        toOther(imageType, bi, dest);
        return true;
    }

    /**
     * 生成小图.
     * @param config
     * @param src
     * @param destDir 如果为null，就在src同目录下生成
     * @return
     * @throws IOException
     */
    public static Map<String,File> genPictures(java.util.List<int[]> sizes,File src,File destDir) throws IOException {
        AssertExt.isTrue((src != null && src.exists()), ERROR_MSG_SOURCE_IMAGE_FILE_NO_EXIST);
        AssertExt.notNull(sizes);

        File realDestDir = null;
        if(destDir!=null){
            realDestDir = destDir;
            if(!destDir.exists()){
                AssertExt.isTrue(destDir.mkdirs());
            }
        }else {
            realDestDir = src.getParentFile();
        }

        String fileName = src.getName();
        HashMap<String,File> files = new HashMap<String,File>();
        Image img = ImageIO.read(src);
        int srcWidth = img.getWidth(null);
        int srcHeight = img.getHeight(null);
        for(int[] size: sizes){
            String sizeStr = size[0] + "X" + size[1];
            File newFile = new File(realDestDir,StringUtils.insertFileNameSuffixToUrl(fileName, "_" + sizeStr));
            int width = size[0];
            int height = size[1];
            if(srcWidth < width && srcHeight < height){
                //如果原图大小比规格小，那么取原图大小
                width = srcWidth;
                height = srcHeight;
            }
            doChangeSize(FilenameUtils.getExtension(fileName),src,newFile,width,height);
            files.put(sizeStr,newFile);
        }
        return files;
    }


//    public void test() throws IOException {
//        ArrayList<String> specs = new ArrayList();
//        specs.add("100X100");
//        specs.add("200X200");
//        ImageGeneratorConfig config = new ImageGeneratorConfig();
//        config.setPicSpecList(specs);
//
//        File src = new File("C:\\Users\\lxd\\Desktop\\iMall 后台 UI设计\\iMall 后台 UI设计\\新建文件夹\\a.png");
//        for(File smallPic:genPictures(config,src,null)){
//            System.out.println(smallPic.getPath());
//        }
//    }

//    public static void main(String[] args){
//        boolean ss = false;
//
//        try{
//            //gif
//            File drcImg = new File("d://gif001.gif");
//            File destImg = new File("d://gif002.gif");
//            ss = ImageUtil.doChangeSize("gif", drcImg, destImg, 400, 300);
//            //jpg
//            drcImg = new File("d://jpg001.jpg");
//            destImg = new File("d://jpg002.jpg");
//            ss = ImageUtil.doChangeSize("jpg", drcImg, destImg, 400, 300);
//            //png
//            drcImg = new File("d://png001.png");
//            destImg = new File("d://png002.png");
//            ss = ImageUtil.doChangeSize("png", drcImg, destImg, 400, 300);
//
//            if (ss){
//                System.out.println("Success");
//            } else{
//                System.out.println("Error");
//            }
//        } catch (Exception e){
//            System.out.print(e.toString());
//        }
//    }


    //TODO打到指定位置
    public static void addWaterMark(String imageType, File src, File waterMarkSrc) {
        try {
            Image iSrc = ImageIO.read(src);
            int width = iSrc.getWidth(null);
            int height = iSrc.getHeight(null);

            BufferedImage bi = null;
            bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bi.createGraphics();
            g.setBackground(Color.WHITE);
            g.fillRect(0, 0, width, height);

            g.drawImage(iSrc, 0, 0, width, height, null);

            Image iWMSrc = ImageIO.read(waterMarkSrc);
            int wmWidth = iWMSrc.getWidth(null);
            int wmHeight = iWMSrc.getHeight(null);
            int w = 0;
            int h = 0;

            //得到水印图高宽比例
            double scale = 1;
            if (wmWidth > wmHeight) {
                scale = ((double) wmHeight) / ((double) wmWidth);
            } else {
                scale = ((double) wmWidth) / ((double) wmHeight);
            }
            //改变水印图高宽以适应原图
            if (width > height) {
                if (wmWidth > wmHeight) {
                    if (wmWidth > width) {
                        wmWidth = width;
                    }
                    wmHeight = new Double(wmWidth * scale).intValue();
                } else {
                    if (wmHeight > height) {
                        wmHeight = height;
                    }
                    wmWidth = new Double(wmHeight * scale).intValue();
                }
            } else {
                if (wmHeight > wmWidth) {
                    if (wmHeight > height) {
                        wmHeight = height;
                    }
                    wmWidth = new Double(wmHeight * scale).intValue();
                } else {
                    if (wmWidth > width) {
                        wmWidth = width;
                    }
                    wmHeight = new Double(wmWidth * scale).intValue();
                }
            }
            //取得水印图在原图的起始坐标
            if (width > wmWidth) {
                w = (width - wmWidth) / 2;
            }
            if (height > wmHeight) {
                h = (height - wmHeight) / 2;
            }
            System.out.println("src width=" + Integer.toString(width) +
                    " height=" +
                    Integer.toString(height) + " water width=" +
                    Integer.toString(wmWidth) + " height=" +
                    Integer.toString(wmHeight) + " left=" +
                    Integer.toString(w) + " top=" +
                    Integer.toString(h));

            iWMSrc = iWMSrc.getScaledInstance(wmWidth, wmHeight,
                    Image.SCALE_SMOOTH);

            g.drawImage(iWMSrc, w, h, wmWidth, wmHeight, null);
            g.dispose();
            if (!imageType.equalsIgnoreCase(IMAGE_TYPE_GIF)) {
                if(imageType.equalsIgnoreCase(IMAGE_TYPE_JPEG)){
                    try {
                        JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpeg").next();
                        ImageOutputStream ios = ImageIO.createImageOutputStream(src);
                        imageWriter.setOutput(ios);
                        // Compression
                        JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
                        jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
                        jpegParams.setCompressionQuality(0.95f);
                        // Metadata (dpi)
                        IIOMetadata data = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(bi), jpegParams);

                        // Write and clean up
                        imageWriter.write(data, new IIOImage(bi, null, null), jpegParams);
                        ios.close();
                        imageWriter.dispose();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    ImageIO.write(bi, imageType, src);
                }
            } else {
                AnimatedGifEncoder age = new AnimatedGifEncoder();
                age.setQuality(1);
                age.start(src.toString());
                age.addFrame(bi);
                age.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addWaterMarkForImage(String imageType, File src, File waterMarkSrc,Integer watermarkLeft,Integer watermarkTop) {
        try{
            Image iSrc = ImageIO.read(src);
            int width = iSrc.getWidth(null);
            int height = iSrc.getHeight(null);

            BufferedImage bi = null;
            bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bi.createGraphics();
            g.setBackground(Color.WHITE);
            g.fillRect(0, 0, width, height);
            g.drawImage(iSrc, 0, 0, width, height, null);

            Image iWMSrc = ImageIO.read(waterMarkSrc);
            int wmWidth = iWMSrc.getWidth(null);
            int wmHeight = iWMSrc.getHeight(null);
            iWMSrc = iWMSrc.getScaledInstance(wmWidth, wmHeight,
                    Image.SCALE_SMOOTH);

            g.drawImage(iWMSrc, watermarkLeft, watermarkTop, wmWidth, wmHeight, null);
            g.dispose();

            System.out.println("src width=" + Integer.toString(width) +
                    " height=" +
                    Integer.toString(height) + " water width=" +
                    Integer.toString(wmWidth) + " height=" +
                    Integer.toString(wmHeight) + " left=" +
                    Integer.toString(watermarkLeft) + " top=" +
                    Integer.toString(watermarkTop));

            if (!imageType.equalsIgnoreCase(IMAGE_TYPE_GIF)) {
                if(imageType.equalsIgnoreCase(IMAGE_TYPE_JPEG)){
                    try {
                        JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpeg").next();
                        ImageOutputStream ios = ImageIO.createImageOutputStream(src);
                        imageWriter.setOutput(ios);
                        // Compression
                        JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
                        jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
                        jpegParams.setCompressionQuality(0.95f);
                        // Metadata (dpi)
                        IIOMetadata data = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(bi), jpegParams);

                        // Write and clean up
                        imageWriter.write(data, new IIOImage(bi, null, null), jpegParams);
                        ios.close();
                        imageWriter.dispose();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    ImageIO.write(bi, imageType, src);
                }
            } else {
                AnimatedGifEncoder age = new AnimatedGifEncoder();
                age.setQuality(1);
                age.start(src.toString());
                age.addFrame(bi);
                age.finish();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

