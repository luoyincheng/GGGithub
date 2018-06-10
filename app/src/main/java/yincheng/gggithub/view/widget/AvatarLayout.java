package yincheng.gggithub.view.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import yincheng.gggithub.R;


/**
 * Created by Kosh on 14 Nov 2016, 7:59 PM
 */

public class AvatarLayout extends FrameLayout {

//    @BindView(R.id.avatar) ShapedImageView avatar;
    @Nullable private String login;
    private boolean isOrg;
    private boolean isEnterprise;

//    @OnClick(R.id.avatar) void onClick(@NonNull View view) {
//        if (InputHelper.isEmpty(login)) return;
//        UserPagerActivity.startActivity(view.getContext(), login, isOrg, isEnterprise, -1);
//    }

    public AvatarLayout(@NonNull Context context) {
        super(context);
    }

    public AvatarLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AvatarLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AvatarLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.avatar_layout, this);
        if (isInEditMode()) return;
        ButterKnife.bind(this);
        setBackground();
//        if (PrefGetter.isRectAvatar()) {
//            avatar.setShape(ShapedImageView.SHAPE_MODE_ROUND_RECT, 15);
//        }
    }

    public void setUrl(@Nullable String url, @Nullable String login, boolean isOrg, boolean isEnterprise) {
        setUrl(url, login, isOrg, isEnterprise, false);
    }

    public void setUrl(@Nullable String url, @Nullable String login, boolean isOrg, boolean isEnterprise, boolean reload) {
        this.login = login;
        this.isOrg = isOrg;
        this.isEnterprise = isEnterprise;
//        avatar.setContentDescription(login);
        if (login != null) {
//            TooltipCompat.setTooltipText(avatar, login);
        } else {
//            avatar.setOnClickListener(null);
//            avatar.setOnLongClickListener(null);
        }
//        Glide.with(getContext())
//                .load(url)
//                .fallback(ContextCompat.getDrawable(getContext(), R.drawable.ic_fasthub_mascot))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .signature(new StringSignature(reload ? String.valueOf(System.currentTimeMillis()) : "0"))
//                .dontAnimate()
//                .into(avatar);
    }
    
    private void setBackground() {
//        if (PrefGetter.isRectAvatar()) {
//            setBackgroundResource(R.drawable.rect_shape);
//        } else {
//            setBackgroundResource(R.drawable.circle_shape);
//        }
    }
}
