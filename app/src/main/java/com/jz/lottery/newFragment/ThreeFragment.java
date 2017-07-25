package com.jz.lottery.newFragment;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jz.lottery.R;
import com.jz.lottery.base.BaseAdapter;
import com.jz.lottery.base.BaseFragment;
import com.jz.lottery.entity.entity;
import com.jz.lottery.newActivity.ZiXunDetialActivity;
import com.jz.lottery.utils.PreferencesObjectUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThreeFragment extends BaseFragment {
    private Callback.Cancelable cancelable;
    private ArrayList<entity> entities;
    ListAdapter listAdapter;
    ListView listView;
    private entity mEntity;
    private RequestParams requestParams;
    View view;

    public class ListAdapter extends BaseAdapter<entity> {
        private ArrayList<entity> mData;
        private Context mcontext;

        /* renamed from: com.wwp.www.vrcpchaxun.newFragment.ThreeFragment.ListAdapter.1 */
        class AnonymousClass1 implements OnClickListener {
            final /* synthetic */ entity val$s;

            AnonymousClass1(entity com_wwp_www_vrcpchaxun_entity_entity) {
                this.val$s = com_wwp_www_vrcpchaxun_entity_entity;
            }

            public void onClick(View v) {
                Intent intent = new Intent(ThreeFragment.this.getActivity(), ZiXunDetialActivity.class);
                intent.putExtra("id", this.val$s.getId());
                ThreeFragment.this.startActivity(intent);
            }
        }

        public ListAdapter(ArrayList<entity> mData, Context mcontext) {
            super(mData, mcontext, R.layout.faxian_item);
            this.mcontext = mcontext;
            this.mData = mData;
        }

        public void setView(ViewHolder vh, entity s, int position) {
            TextView mTitle = (TextView) vh.findById(R.id.title);
            LinearLayout faxian_ll = (LinearLayout) vh.findById(R.id.faxian_ll);
            ((TextView) vh.findById(R.id.time)).setText(s.getPubdate());
            mTitle.setText(s.getTitle());
            faxian_ll.setOnClickListener(new AnonymousClass1(s));
        }
    }

    public ThreeFragment() {
        this.mEntity = new entity();
        this.entities = new ArrayList();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_three, container, false);
        return this.view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        this.entities = (ArrayList) PreferencesObjectUtil.readObject("data", getContext());
        if (this.entities == null || this.entities.size() == 0) {
            getzixun();
            return;
        }
        this.listAdapter = new ListAdapter(this.entities, getActivity());
        this.listView.setAdapter(this.listAdapter);
    }

    private void initView() {
        this.listView = (ListView) this.view.findViewById(R.id.zixun_list);
    }

    private void getzixun() {
        this.requestParams = null;
        this.requestParams = new RequestParams("http://www.wozhongla.com/news/API/JSON/list.php?row=80&tid=910");
        this.cancelable = x.http().get(this.requestParams, new Callback.CommonCallback<String>() {
            public void onSuccess(String result) {
                System.out.println("WWW---" + result);
                ThreeFragment.this.entities = (ArrayList) ThreeFragment.getObjectList(result, entity.class);
                if (ThreeFragment.this.entities != null && ThreeFragment.this.entities.size() > 0) {
                    PreferencesObjectUtil.saveObject(ThreeFragment.this.entities, "data", ThreeFragment.this.getActivity());
                    if (ThreeFragment.this.listAdapter == null) {
                        ThreeFragment.this.listAdapter = new ListAdapter(ThreeFragment.this.entities, ThreeFragment.this.getActivity());
                        ThreeFragment.this.listView.setAdapter(ThreeFragment.this.listAdapter);
                        return;
                    }
                    ThreeFragment.this.listAdapter.notifyDataSetChanged();
                }
            }

            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(ThreeFragment.this.getActivity(), "\u7f51\u7edc\u4e0d\u53ef\u7528\u8bf7\u68c0\u67e5\u7f51\u7edc\u72b6\u6001", Toast.LENGTH_SHORT).show();
                System.out.println("WWW---" + ex.toString());
            }

            public void onCancelled(CancelledException cex) {
            }

            public void onFinished() {
            }
        });
    }

    public static <T> List<T> getObjectList(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList();
        try {
            Gson gson = new Gson();
            Iterator it = new JsonParser().parse(jsonString).getAsJsonArray().iterator();
            while (it.hasNext()) {
                list.add((T) gson.fromJson((JsonElement) it.next(), (Class) cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}