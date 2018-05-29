package yincheng.gggithub.library.materialbottomnavigation;

import android.view.View;

/**
 * Created by alessandro on 4/4/16 at 11:14 PM.
 * Project: Material-BottomNavigation
 */
public interface OnItemClickListener {
    void onItemClick(ItemsLayoutContainer parent, View view, int index, boolean animate);

    void onItemPressed(ItemsLayoutContainer parent, View view, boolean pressed);
}
