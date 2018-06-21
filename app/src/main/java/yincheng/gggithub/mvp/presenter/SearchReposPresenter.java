package yincheng.gggithub.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;
import yincheng.gggithub.R;
import yincheng.gggithub.helper.AppHelper;
import yincheng.gggithub.helper.InputHelper;
import yincheng.gggithub.mvp.contract.SearchReposContract;
import yincheng.gggithub.mvp.model.Code;
import yincheng.gggithub.mvp.model.GithubUser;
import yincheng.gggithub.mvp.model.Issue;
import yincheng.gggithub.mvp.model.Repo;
import yincheng.gggithub.mvparchitecture.ViewAction;
import yincheng.gggithub.provider.network.Pageable;
import yincheng.gggithub.provider.network.ServiceProvider;
import yincheng.gggithub.view.widget.FontAutoCompleteEditText;
import yincheng.gggithub.view.widget.TagGroup;

/**
 * Created by yincheng on 2018/6/4/17:13.
 * github:luoyincheng
 */
public class SearchReposPresenter extends RxPresenter<SearchReposContract.View> implements
      SearchReposContract.Presenter {
   private ArrayList<Repo> repoArrayList = new ArrayList<>();
   private ArrayList<GithubUser> userArrayList = new ArrayList<>();
   private ArrayList<Issue> issueArrayList = new ArrayList<>();
   private ArrayList<Code> codeArrayList = new ArrayList<>();
   private int page;
   private int previousTotalItems;
   private int lastPage = Integer.MAX_VALUE;

   @Override protected void onAttachView(@NonNull SearchReposContract.View view) {
      Logger.i("MVP");
      super.onAttachView(view);
   }

   @NonNull @Override public ArrayList<Repo> getRepoList() {
      return repoArrayList;
   }

   @NonNull @Override public ArrayList<GithubUser> getUserList() {
      return userArrayList;
   }

   @NonNull @Override public ArrayList<Issue> getIssueList() {
      return issueArrayList;
   }

   @NonNull @Override public ArrayList<Code> getCodeList() {
      return codeArrayList;
   }

   //      @Override public void onSearch(@NonNull FontAutoCompleteEditText editText, TagGroup
//    tagGroup,
//                                  String searchType) {
//      sendToView(view -> view.onToggleFilter(false));
//      boolean isQualified = !InputHelper.isEmpty(editText) && InputHelper.toString(editText)
//            .length() > 1 && !InputHelper.isEmpty(searchType);
//      editText.setError(isQualified ? null : editText.getResources().getString(R.string
//            .search_key_size_required));
//      if (isQualified) {
//         String searchKey = InputHelper.toString(editText);
//         tagGroup.appendInputTag(searchKey);
//         editText.dismissDropDown();
//         AppHelper.hideKeyboard(editText);
//         onCallApi(searchType, 1, searchKey);
//      }
//   }
   @Override public void onSearch(@NonNull FontAutoCompleteEditText editText, TagGroup tagGroup,
                                  SearchReposContract.SearchType searchType) {
      sendToView(view -> view.onToggleFilter(false));
      boolean isQualified = !InputHelper.isEmpty(editText) && InputHelper.toString(editText)
            .length() > 1 && !InputHelper.isEmpty(searchType);
      editText.setError(isQualified ? null : editText.getResources().getString(R.string
            .search_key_size_required));
      if (isQualified) {
         String searchKey = InputHelper.toString(editText);
         tagGroup.appendInputTag(searchKey);
         editText.dismissDropDown();
         AppHelper.hideKeyboard(editText);
         onCallApi(searchType, 1, searchKey);
      }
   }

   //this is no generated from "getter and setter",this is from Presenter
   @Override public int getCurrentPage() {
      return page;
   }

   //this is no generated from "getter and setter",this is from Presenter
   @Override public int getPreviousTotal() {
      return previousTotalItems;
   }

   //this is no generated from "getter and setter",this is from Presenter
   @Override public void setCurrentPage(int currentPage) {
      this.page = currentPage;
   }

   //this is no generated from "getter and setter",this is from Presenter
   @Override public void setPreviousTotal(int previousTotal) {
      this.previousTotalItems = previousTotal;
   }

//   @Override public boolean onCallApi(String paramInPath, int page, @Nullable String parameter) {
//      if (page == 1) {
//         lastPage = Integer.MAX_VALUE;
//         sendToView(new ViewAction<SearchReposContract.View>() {
//            @Override public void call(SearchReposContract.View view) {
//               view.getLoadMoreClass().reset();
//            }
//         });
//      }
//      setCurrentPage(page);
//      if (page > lastPage || lastPage == 0 || parameter == null) {
//         sendToView(new ViewAction<SearchReposContract.View>() {
//            @Override public void call(SearchReposContract.View view) {
//               view.hideProgressView();
//            }
//         });
//         return false;
//      }
//      makeRestCall(ServiceProvider.getSearchService(false).searchRepositories(paramInPath,
//            parameter, page),
//            new Consumer<Pageable<Repo>>() {
//               @Override public void accept(Pageable<Repo> repoPageable) throws Exception {
//                  lastPage = repoPageable.getLast();
//                  sendToView(new ViewAction<SearchReposContract.View>() {
//                     @Override public void call(SearchReposContract.View view) {
//                        view.onNotifyAdapter(repoPageable.isIncompleteResults() ? null :
//                              repoPageable.getItems(), page);
//                        if (!repoPageable.isIncompleteResults())//请求不完整
//                           view.onSetTabConut(repoPageable.getTotalCount());
//                        else {
//                           view.onSetTabConut(0);
//                           view.showMessage(R.string.error, R.string.search_result_warning);
//                        }
//                     }
//                  });
//               }
//            });
//      return true;
//   }

   @Override public boolean onCallApi(SearchReposContract.SearchType searchType, int page,
                                      @Nullable String parameter) {
      if (page == 1) {
         lastPage = Integer.MAX_VALUE;
         sendToView(new ViewAction<SearchReposContract.View>() {
            @Override public void call(SearchReposContract.View view) {
               view.getLoadMoreClass().reset();
            }
         });
      }
      setCurrentPage(page);
      if (page > lastPage || lastPage == 0 || parameter == null) {
         sendToView(new ViewAction<SearchReposContract.View>() {
            @Override public void call(SearchReposContract.View view) {
               view.hideProgressView();
            }
         });
         return false;
      }
      switch (searchType) {
         default:
            makeRestCall(ServiceProvider.getSearchService(false).searchRepositories(
                  parameter, page),
                  repoPageable -> {
                     lastPage = repoPageable.getLast();
                     sendToView(new ViewAction<SearchReposContract.View>() {
                        @Override public void call(SearchReposContract.View view) {
                           view.onNotifyAdapter(repoPageable.isIncompleteResults() ? null :
                                 repoPageable.getItems(), page);
                           if (!repoPageable.isIncompleteResults())//请求不完整
                              view.onSetTabConut(repoPageable.getTotalCount());
                           else {
                              view.onSetTabConut(0);
                              view.showMessage(R.string.error, R.string.search_result_warning);
                           }
                        }
                     });
                  });
            break;
      }
      return true;
   }

   @Override public void onFocusChange(View v, boolean hasFocus) {
      if (v instanceof FontAutoCompleteEditText)
         sendToView(view -> view.onToggleFilter(hasFocus));
   }
}
