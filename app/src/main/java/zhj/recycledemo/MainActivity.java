package zhj.recycledemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private List<String> mData;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdata();
        initview();
    }

    private void initdata() {
        mData = new ArrayList<String>();
        for (int i = 0; i < 45; i++) {
            mData.add("item" + i);
        }
    }

    private void initview() {

        mRecycleView = (RecyclerView) findViewById(R.id.recyclerView);

        myAdapter = new MyAdapter(mData);
        mRecycleView.setAdapter(myAdapter);//设置适配器

        //设置布局管理器 , 将布局设置成纵向
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
//        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));

        //设置分隔线
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.VERTICAL_LIST));
        //设置增加或删除条目动画
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

    }

    public void add(View v) {
        myAdapter.addData(1);
    }

    public void delete(View v) {
        myAdapter.deleateData(1);
    }

    public void linear(View v) {
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    public void grid(View v) {
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));

    }

    public void staggered(View v) {
        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));

    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<String> mDatas;
        private List<Integer> mHeight;

        //创建构造参数，用来接受数据集
        public MyAdapter(List<String> datas) {
            this.mDatas = datas;
            mHeight = new ArrayList<Integer>();
            //随机获取一个mHeight值
            for (int i = 0; i < mDatas.size(); i++) {
                mHeight.add((int) (200 + Math.random() * 200));
            }
        }

        //创建ViewHolder
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //加载布局文件
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        //绑定ViewHolder
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            //将数据填充到具体的view中
            holder.tv.setText(mDatas.get(position));

            //绑定数据的同时，修改每个ItemView的高度
//
//                ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//                lp.height = mHeight.get(position);
//                holder.itemView.setLayoutParams(lp);

        }


        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        //新增item
        public void addData(int pos) {
            mDatas.add(pos, "新增");
            notifyItemInserted(pos);
        }

        //移除item
        public void deleateData(int pos) {
            mDatas.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.recycle_tv);

        }
    }

}
