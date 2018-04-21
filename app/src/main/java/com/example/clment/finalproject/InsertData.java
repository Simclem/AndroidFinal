package com.example.clment.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InsertData.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InsertData#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertData extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public EditText Debug;
    public Button Bouton;
    private OnFragmentInteractionListener mListener;

    public InsertData() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertData.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertData newInstance(String param1, String param2) {
        InsertData fragment = new InsertData();
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


        View view = inflater.inflate(R.layout.fragment_insert_data,
                container, false);
        Bouton = (Button) view.findViewById(R.id.button3);

        Debug = (EditText) view.findViewById(R.id.Debug);


        Bouton.setOnClickListener(this);


        return inflater.inflate(R.layout.fragment_insert_data, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void getDataFromDB(){
        try{
            Debug.setText("test");
            DBGestionnaire Gestionnaire = new DBGestionnaire(getContext());
            Concert concert = new Concert("Boney M", "Rasputin");
            Gestionnaire.open(getContext());
            Gestionnaire.insertLivre(concert);
            Concert livreFromBdd = Gestionnaire.getConcert();
            //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
            if(livreFromBdd != null){
                //On affiche les infos du livre dans un Toast
                Debug.setText(livreFromBdd.toString());
                Toast.makeText(getContext(), livreFromBdd.toString(), Toast.LENGTH_LONG).show();
                //On modifie le titre du livre
                //Puis on met à jour la BDD

            }
        } catch (Exception E){
            Toast.makeText(getContext(), E.toString(), Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }/* else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button3:


            break;
        }
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
