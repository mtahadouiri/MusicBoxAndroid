package com.esprit.mtdev.MusicBox.fragments.MenuFragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.esprit.mtdev.MusicBox.R;
import com.esprit.mtdev.MusicBox.activities.HomeActivity;
import com.esprit.mtdev.MusicBox.activities.SplashActivity;
import com.esprit.mtdev.MusicBox.clickitemtouchlistener.ClickItemTouchListener;
import com.esprit.mtdev.MusicBox.custombottomsheets.CustomLocalBottomSheetDialog;
import com.esprit.mtdev.MusicBox.fragments.LocalMusicFragments.AlbumFragment;
import com.esprit.mtdev.MusicBox.fragments.LocalMusicFragments.AlbumRecyclerAdapter;
import com.esprit.mtdev.MusicBox.fragments.LocalMusicFragments.ArtistFragment;
import com.esprit.mtdev.MusicBox.fragments.LocalMusicFragments.LocalMusicFragment;
import com.esprit.mtdev.MusicBox.fragments.LocalMusicFragments.LocalMusicViewPagerFragment;
import com.esprit.mtdev.MusicBox.fragments.LocalMusicFragments.LocalTrackRecyclerAdapter;
import com.esprit.mtdev.MusicBox.fragments.LocalMusicFragments.RecentlyAddedSongsFragment;
import com.esprit.mtdev.MusicBox.models.LocalTrack;
import com.esprit.mtdev.MusicBox.models.ProductType;
import com.esprit.mtdev.MusicBox.models.UnifiedTrack;
import com.esprit.mtdev.MusicBox.utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuHomeFragment.OnMenuHomeSelectedListener} interface
 * to handle interaction events.
 * Use the {@link MenuHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuHomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ProductTypesRecyclerAdapter abAdapter;

    public RecyclerView rv;

    public MenuHomeFragment.onProductTypeCLickListener mCallback;
    GridLayoutManager glManager;

    View bottomMarginLayout;
    ImageView backBtn;
    public ImageView searchIcon;
    public TextView fragTitle;
    public EditText searchBox;

    public boolean isSearchboxVisible = false;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Context ctx;
    HomeActivity activity;

    private ShowcaseView showCase;

    public interface onProductTypeCLickListener {
        public void onProductTypeCLick();
    }
    public MenuHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuHomeFragment newInstance(String param1, String param2) {
        MenuHomeFragment fragment = new MenuHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_home, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ((HomeActivity) getActivity()).onQueryTextChange("");
        isSearchboxVisible = false;

        backBtn = (ImageView) view.findViewById(R.id.local_fragment_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        fragTitle = (TextView) view.findViewById(R.id.local_fragment_title);
        if (SplashActivity.tf4 != null)
            fragTitle.setTypeface(SplashActivity.tf4);

        searchBox = (EditText) view.findViewById(R.id.local_fragment_search_box);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((HomeActivity) getActivity()).onQueryTextChange(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchIcon = (ImageView) view.findViewById(R.id.local_fragment_search_icon);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSearchboxVisible) {
                    searchBox.setText("");
                    searchBox.setVisibility(View.INVISIBLE);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    searchIcon.setImageResource(R.drawable.ic_search);
                    fragTitle.setVisibility(View.VISIBLE);
                } else {
                    searchBox.setVisibility(View.VISIBLE);
                    searchBox.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    searchIcon.setImageResource(R.drawable.ic_cross_white);
                    fragTitle.setVisibility(View.INVISIBLE);
                }
                isSearchboxVisible = !isSearchboxVisible;
            }
        });


        rv = (RecyclerView) view.findViewById(R.id.albums_recycler);
        abAdapter = new ProductTypesRecyclerAdapter(ProductType.ProtoProdyctType(), getContext());
        glManager = new GridLayoutManager(getContext(), 2);
        rv.setLayoutManager(glManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(abAdapter);

        rv.addOnItemTouchListener(new ClickItemTouchListener(rv) {
            @Override
            public boolean onClick(RecyclerView parent, View view, int position, long id) {
                HomeActivity.tempMenu = ProductType.ProtoProdyctType().get(position);
                mCallback.onProductTypeCLick();
                return true;
            }

            @Override
            public boolean onLongClick(RecyclerView parent, View view, int position, long id) {
                return false;
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });



    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMenuHomeSelectedListener {
        // TODO: Update argument type and name
        void OnMenuHomeSelected(Uri uri);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (MenuHomeFragment.onProductTypeCLickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }
    public void updateAdapter() {
        if (abAdapter != null)
            abAdapter.notifyDataSetChanged();
    }

}
