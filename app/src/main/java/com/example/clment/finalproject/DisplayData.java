package com.example.clment.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.crypto.Cipher;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DisplayData.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DisplayData#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayData extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public View rootView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public TextView txt;
    private OnFragmentInteractionListener mListener;

    public DisplayData() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayData.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayData newInstance(String param1, String param2) {
        DisplayData fragment = new DisplayData();
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
        LayoutInflater  layout  = getActivity().getLayoutInflater();


        rootView = inflater.inflate(R.layout.fragment_display_data,
                container, false);

        txt =(TextView) rootView.findViewById(R.id.Result);
        Toast.makeText(getContext(), txt.getText(), Toast.LENGTH_LONG).show();

        Toast.makeText(getContext(), txt.getText(), Toast.LENGTH_LONG).show();


        DBGestionnaire Gestionnaire = new DBGestionnaire(getActivity());
        Gestionnaire.open(getActivity());
        ArrayList<Concert> livreFromBdd = Gestionnaire.getAllConcert();
        String Data = "";
        if(livreFromBdd != null){
            for(int i = 0 ; i< livreFromBdd.size(); i ++){
                Data = Data + livreFromBdd.get(i).getNomConcert() +"    " + livreFromBdd.get(i).getNomGroupe() +"\n";
            }
            txt.setText(Data);

        }
        else{
            txt.setText("Vous n'avez pas de concert");

        }
        Toast.makeText(getContext(), Data, Toast.LENGTH_LONG).show();


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    //on est elle appelée
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public void setText(String str){
        txt.setText(str);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } //else {
        //throw new RuntimeException(context.toString()
        //          + " must implement OnFragmentInteractionListener");
        //}
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
