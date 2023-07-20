package com.example.myfpl.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myfpl.R;
import com.example.myfpl.util.DateUtil;
import com.example.myfpl.util.StringUtil;

public class HeaderApp extends RelativeLayout implements View.OnClickListener {

    private LinearLayout userContainer, centerContainer, leftContainer, rightContainer, labelContainer;
    private ImageView avatar, buttonLeft, buttonRight;
    private TextView sessionText, userName, title, textRightButton;
    private boolean isUserHeader;
    private RelativeLayout parentContainer;
    private HeaderHandleClickListener listener;

    public HeaderApp(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.header_component, this);
        TypedArray style = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HeaderApp, 0, 0);
        init(style);
    }

    public HeaderApp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.header_component, this);
        TypedArray style = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HeaderApp, 0, 0);
        init(style);
    }

    public HeaderApp(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater.from(context).inflate(R.layout.header_component, this);
        TypedArray style = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HeaderApp, 0, 0);
        init(style);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void init(TypedArray style) {
        parentContainer = findViewById(R.id.parentContainer);
        leftContainer = findViewById(R.id.left_container);
        centerContainer = findViewById(R.id.center_container);
        rightContainer = findViewById(R.id.right_container);
        userContainer = findViewById(R.id.user_container);
        labelContainer = findViewById(R.id.label_user_container);

        buttonLeft = findViewById(R.id.button_left);
        buttonRight = findViewById(R.id.button_right);
        avatar = findViewById(R.id.avatar_image);
        title = findViewById(R.id.title_header);
        userName = findViewById(R.id.user_name);
        sessionText = findViewById(R.id.text_session);
        textRightButton = findViewById(R.id.text_button_right);

        try {
//            handle left container
            isUserHeader = style.getBoolean(R.styleable.HeaderApp_app_is_user_container, false);
            userContainer.setVisibility(isUserHeader ? VISIBLE : GONE);
            buttonLeft.setVisibility(isUserHeader ? GONE : VISIBLE);

            if (isUserHeader) {
                sessionText.setText(DateUtil.getCurrentSession());
                String userNameText = style.getString(R.styleable.HeaderApp_app_user_name_text);
                Drawable avt = style.getDrawable(R.styleable.HeaderApp_app_avatar_src);

                userName.setText(StringUtil.nullOrEmpty(userNameText) ? userNameText : "Default Name");
                avatar.setImageDrawable(avt != null ? avt : getResources().getDrawable(R.drawable.avt, getContext().getTheme()));
            } else {
                Drawable iconBtnLeft = style.getDrawable(R.styleable.HeaderApp_app_button_left_src);
                buttonLeft.setImageDrawable(iconBtnLeft != null ? iconBtnLeft : getResources().getDrawable(R.drawable.ic_back, getContext().getTheme()));
            }
//            end handle left container

//            handle center container
            centerContainer.setVisibility(isUserHeader ? GONE : VISIBLE);
            if (!isUserHeader) {
                String titleText = style.getString(R.styleable.HeaderApp_app_header_title);
                title.setText(StringUtil.nullOrEmpty(titleText) ? titleText : "TextDefault");
            }
//            end handle center container

//            handle right container
            boolean enableRightButton = style.getBoolean(R.styleable.HeaderApp_app_enable_right_button, true);
            if (enableRightButton) {
                rightContainer.setVisibility(VISIBLE);
                boolean isTextRightButton = style.getBoolean(R.styleable.HeaderApp_app_is_button_text, false);
                textRightButton.setVisibility(isTextRightButton ? VISIBLE : GONE);
                buttonRight.setVisibility(isTextRightButton ? GONE : VISIBLE);

                if (isTextRightButton) {
                    String textButtonRight = style.getString(R.styleable.HeaderApp_app_text_button_right);
                    textRightButton.setText(StringUtil.nullOrEmpty(textButtonRight) ? textButtonRight : "Default Text");
                } else {
                    Drawable iconButtonRight = style.getDrawable(R.styleable.HeaderApp_app_right_button_src);
                    buttonRight.setImageDrawable(iconButtonRight != null ? iconButtonRight : getResources().getDrawable(R.drawable.ic_notify, getContext().getTheme()));
                }
            } else {
                rightContainer.setVisibility(GONE);
            }

//            end handle right container
        } catch (Exception e) {

        } finally {
            style.recycle();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        clickListener();
    }

    public void setHeaderVisibility(boolean visibility) {
        parentContainer.setVisibility(visibility ? VISIBLE : GONE);
        requestLayout();
    }

    public void clickListener() {
        buttonRight.setOnClickListener(HeaderApp.this);
        buttonLeft.setOnClickListener(HeaderApp.this);
        textRightButton.setOnClickListener(HeaderApp.this);
        userContainer.setOnClickListener(HeaderApp.this);
    }

    public HeaderApp setHeaderClickListener(HeaderHandleClickListener listener) {
        this.listener = listener;
        return this;
    }

    public void hiddenLabelContainer(boolean visibility) {
        if (labelContainer != null) {
            labelContainer.setVisibility(visibility ? GONE : VISIBLE);
            requestLayout();
        }
    }

    public void setHeaderTitle(String textTitle) {
        if (title != null) {
            title.setText(textTitle);
            requestLayout();
        }
    }

    public void setUserName(String name){
        if(userName != null){
            userName.setText(name);
            requestLayout();
        }
    }

    public void setUserAvatar(int resId) {
        if (avatar != null) {
            avatar.setImageResource(resId);
            requestLayout();
        }
    }

    public void setUserAvatar(String url) {
        if (avatar != null) {
            Glide.with(getContext()).load(url)
                    .override(70, 70).placeholder(R.drawable.img_placeholder)
                    .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(avatar);
            requestLayout();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (listener == null) return;
        switch (view.getId()) {
            case R.id.user_container:
                if (userContainer != null && isUserHeader) {
                    listener.onUserContainerClick();
                }
                break;
            case R.id.button_right:
            case R.id.text_button_right:
                if (rightContainer != null) {
                    listener.onRightButtonClick();
                }
                break;
            case R.id.button_left:
                if (buttonLeft != null) {
                    listener.onLeftButtonClick();
                }
                break;
        }
    }

    public interface HeaderHandleClickListener {
        void onLeftButtonClick();

        void onUserContainerClick();

        void onRightButtonClick();
    }
}
