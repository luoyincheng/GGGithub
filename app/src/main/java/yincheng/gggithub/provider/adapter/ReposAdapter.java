package yincheng.gggithub.provider.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.List;

import yincheng.gggithub.library.recyclerview.BaseRecyclerAdapter;
import yincheng.gggithub.library.recyclerview.BaseViewHolder;
import yincheng.gggithub.mvp.model.Repo;
import yincheng.gggithub.provider.viewholder.ReposViewHolder;

public class ReposAdapter extends
      BaseRecyclerAdapter<Repo, ReposViewHolder, BaseViewHolder.OnItemClickListener<Repo>> {
   private boolean isStarred;
   private boolean withImage;

   public ReposAdapter(@NonNull List<Repo> data, boolean isStarred) {
      this(data, isStarred, false);
   }

   public ReposAdapter(@NonNull List<Repo> data, boolean isStarred, boolean withImage) {
      super(data);
      this.isStarred = isStarred;
      this.withImage = withImage;
   }

   @Override protected ReposViewHolder viewHolder(ViewGroup parent, int viewType) {
      Logger.i("创建了viewholder");
      return ReposViewHolder.newInstance(parent, this, isStarred, withImage);
   }

   @Override protected void onBindView(ReposViewHolder holder, int position) {
      holder.bind(getItem(position));
   }
}
