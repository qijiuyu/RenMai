package com.example.administrator.renmai.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import com.example.administrator.renmai.R;
import java.io.File;
/**
 * 选择图片工具类
 * Created by Administrator on 2017/12/29 0029.
 */

public class PicturesUtil {

    /* 请求识别码 */
    public static final int CODE_GALLERY_REQUEST = 0xa0;
    public static final int CODE_CAMERA_REQUEST = 0xa1;
    public static final int CODE_RESULT_REQUEST = 0xa2;
    public static final String pai = FileUtils.getSdcardPath() + "pictures.jpg";
    public static final String crop = FileUtils.getSdcardPath() + "crop.jpg";
    public static final String compress = FileUtils.getSdcardPath() + "compress.jpg";

    public static void selectPhoto(final Activity context) {
        File file=new File(crop);
        if(null!=file && file.isFile()){
            file.delete();
        }
        View view_choise = LayoutInflater.from(context).inflate(R.layout.select_photo_dialog, null);
        final Dialog dialog  = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle(null);
        dialog.setCancelable(true);
        dialog.setContentView(view_choise);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.mystyle);  //添加动画
        dialog.show();
        //拍照
        view_choise.findViewById(R.id.textview_choseimg_camera).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(pai)));
                context.startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
            }
        });
        //本地相册选择
        view_choise.findViewById(R.id.textview_choseimg_gallery).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                Intent intentFromGallery = new Intent();
                // 设置文件类型
                intentFromGallery.setType("image/*");
                intentFromGallery.setAction(Intent.ACTION_PICK);
                context.startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
            }
        });
    }
}
