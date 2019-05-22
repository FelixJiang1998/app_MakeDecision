package edu.cczu.makedecision.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.cczu.makedecision.MainActivity;
import edu.cczu.makedecision.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int CONTEXT_MENU_view = 10;
    private static final int CONTEXT_MENU_edit = 11;
    private static final int CONTEXT_MENU_delete = 12;
    ArrayAdapter<String> arrayAdapter_event;
    private ArrayList<String> events;
    private Context mContext;
    private String event1 = "吃什么";
    private String event2 = "Yes or No";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = view.findViewById(R.id.ListView_events);
        ArrayList<String> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        arrayAdapter_event = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, events);
        listView.setAdapter(arrayAdapter_event);
//        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//            @Override
//            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//                contextMenu.add(0, CONTEXT_MENU_view, 0, "查看");
//                contextMenu.add(0, CONTEXT_MENU_edit, 1, "编辑");
//                contextMenu.add(0, CONTEXT_MENU_delete, 2, "删除");
//            }
//        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.set_tab(2);
                switch (i) {
                    case 0:
                        Toast.makeText(getActivity(), "吃什么", Toast.LENGTH_SHORT).show();
                    case 1:
                        Toast.makeText(getActivity(), "Yes Or No", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        int id = info.position;
//        String event = events.get(id);
//        switch (item.getItemId()) {
//            default:
//                return false;
//            case CONTEXT_MENU_view:
//                Toast.makeText(mContext.getApplicationContext(), event, Toast.LENGTH_SHORT);
//                return true;
//            case CONTEXT_MENU_edit:
//                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
//
//                final EditText input = new EditText(mContext);
//                input.setText(event);
//                alert.setView(input);
//
//                alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        String updatedName = input.getText().toString();
//                        event1 = updatedName;
//                    }
//                });
//
//                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // if this button is clicked, just close
//                        // the dialog box and do nothing
//                        dialog.cancel();
//                    }
//                });
//
//                alert.show();
//                return true;
//            case CONTEXT_MENU_delete:
//                events.remove(event);
//                arrayAdapter_event.notifyDataSetChanged();
//                return true;
//        }
//    }

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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
