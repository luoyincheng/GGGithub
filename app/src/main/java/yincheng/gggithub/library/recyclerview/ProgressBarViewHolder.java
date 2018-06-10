package yincheng.gggithub.library.recyclerview;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import yincheng.gggithub.R;


/**
 * Created by kosh on 03/08/2017.
 */

public class ProgressBarViewHolder extends BaseViewHolder {

    private ProgressBarViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public static ProgressBarViewHolder newInstance(ViewGroup viewGroup) {
        return new ProgressBarViewHolder(getView(viewGroup, R.layout.progress_layout));
    }

    @Override public void bind(@NonNull Object o) {

    }
}
