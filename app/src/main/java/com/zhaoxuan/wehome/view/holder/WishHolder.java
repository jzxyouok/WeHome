package com.zhaoxuan.wehome.view.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhaoxuan.wehome.R;
import com.zhaoxuan.wehome.framework.base.BaseRecyclerHolder;
import com.zhaoxuan.wehome.support.dto.WishDto;
import com.zhaoxuan.wehome.support.utils.StrUtils;

/**
 * Created by lizhaoxuan on 16/2/24.
 */
public class WishHolder extends BaseRecyclerHolder<WishDto> {

    protected ImageView wishImg;
    protected TextView titleText;
    protected TextView timeText;

    public WishHolder(Context context, int layoutId, ViewGroup parent, ItemClickListener itemClickListener) {
        super(LayoutInflater.from(context).inflate(layoutId, parent, false), itemClickListener);

        wishImg = (ImageView) view.findViewById(R.id.wishImg);
        titleText = (TextView) view.findViewById(R.id.titleText);
        timeText = (TextView) view.findViewById(R.id.timeText);
    }

    @Override
    public void updateView(WishDto data) {
        titleText.setText(data.getTitle());
        timeText.setText(data.getTime());
        if (!StrUtils.isNullStr(data.getImgUrl())){
            Bitmap bitmap = BitmapFactory.decodeFile(data.getImgUrl());
            wishImg.setImageBitmap(bitmap);
        }
    }
}
