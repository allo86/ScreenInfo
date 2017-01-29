package com.allo.screeninfo.density;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.allo.screeninfo.R;
import com.allo.screeninfo.base.BaseFragment;
import com.allo.screeninfo.utils.DividerItemDecoration;
import com.allo.screeninfo.utils.KeyValueAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DensityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DensityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DensityFragment extends BaseFragment {

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
    public interface OnFragmentInteractionListener {
    }

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.rv_items)
    RecyclerView mRecyclerView;

    private KeyValueAdapter<ScreenInfo> mAdapter;
    private List<ScreenInfo> items;

    public DensityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DensityFragment.
     */
    public static DensityFragment newInstance() {
        return new DensityFragment();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_density;
    }

    @Override
    public void initializeUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        items = new ArrayList<>();
        mAdapter = new KeyValueAdapter<>(items);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showData() {
        items.add(screenDensity());
        items.add(screenDensityTypeByCode());
        items.add(screenDensityTypeByResources());

        items.add(isPhone());
        items.add(is7Tablet());
        items.add(is10Tablet());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private ScreenInfo screenDensity() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_density));
        screenInfo.setValue(String.valueOf(getResources().getDisplayMetrics().density));
        return screenInfo;
    }

    private ScreenInfo screenDensityTypeByCode() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_density_type_code));

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        switch (metrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                screenInfo.setValue(getString(R.string.screen_density_low));
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                screenInfo.setValue(getString(R.string.screen_density_medium));
                break;
            case DisplayMetrics.DENSITY_HIGH:
                screenInfo.setValue(getString(R.string.screen_density_high));
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                screenInfo.setValue(getString(R.string.screen_density_xhigh));
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                screenInfo.setValue(getString(R.string.screen_density_xxhigh));
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                screenInfo.setValue(getString(R.string.screen_density_xxxhigh));
                break;
            case DisplayMetrics.DENSITY_260:
                screenInfo.setValue(getString(R.string.screen_density_260));
                break;
            case DisplayMetrics.DENSITY_280:
                screenInfo.setValue(getString(R.string.screen_density_280));
                break;
            case DisplayMetrics.DENSITY_300:
                screenInfo.setValue(getString(R.string.screen_density_300));
                break;
            case DisplayMetrics.DENSITY_340:
                screenInfo.setValue(getString(R.string.screen_density_340));
                break;
            case DisplayMetrics.DENSITY_360:
                screenInfo.setValue(getString(R.string.screen_density_360));
                break;
            case DisplayMetrics.DENSITY_400:
                screenInfo.setValue(getString(R.string.screen_density_400));
                break;
            case DisplayMetrics.DENSITY_420:
                screenInfo.setValue(getString(R.string.screen_density_420));
                break;
            default:
                screenInfo.setValue(getString(R.string.screen_density_non_reliable));
                break;
        }

        return screenInfo;
    }

    private ScreenInfo screenDensityTypeByResources() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_density_type_resources));
        screenInfo.setValue(getString(R.string.screen_density_value));
        return screenInfo;
    }

    private ScreenInfo isPhone() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_is_phone));
        screenInfo.setValue(getTextForBoolean(getResources().getBoolean(R.bool.is_phone)));
        return screenInfo;
    }

    private ScreenInfo is7Tablet() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_is_seven_inches_tablet));
        screenInfo.setValue(getTextForBoolean(getResources().getBoolean(R.bool.is_tablet_seven_inches)));
        return screenInfo;
    }

    private ScreenInfo is10Tablet() {
        ScreenInfo screenInfo = new ScreenInfo();
        screenInfo.setKey(getString(R.string.screen_is_ten_inches_tablet));
        screenInfo.setValue(getTextForBoolean(getResources().getBoolean(R.bool.is_tablet_ten_inches)));
        return screenInfo;
    }

    private String getTextForBoolean(boolean item) {
        return getString(item ? R.string.screen_yes : R.string.screen_no);
    }
}
