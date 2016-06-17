package com.zhaoxuan.wehome.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.zhaoxuan.wehome.R;
import com.zhaoxuan.wehome.framework.base.BaseFragment;
import com.zhaoxuan.wehome.module.manager.UserManager;
import com.zhaoxuan.wehome.view.activity.ChatActivity;

import butterknife.Bind;

/**
 * Created by lizhaoxuan on 15/11/24.
 */
public class ChatFragment extends BaseFragment {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.addBtn)
    protected ImageButton addBtn;

    private View view;

    private ChatActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (ChatActivity) getActivity();
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ico_action_menu);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(UserManager.getInstance().getUserDto().getHomeName());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openDrawerLayout();
            }
        });

        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isEnabled = activity.isRefreshLayoutEnabled();

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0 && !isEnabled){
                    activity.setRefreshLayoutEnabled(true);
                    isEnabled = activity.isRefreshLayoutEnabled();
                }else if (verticalOffset!= 0 && isEnabled){
                    activity.setRefreshLayoutEnabled(false);
                    isEnabled = activity.isRefreshLayoutEnabled();
                }
            }
        });


    }


}
