package com.smitcoderx.learn.bucketlist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ModalBottomSheet extends BottomSheetDialogFragment {

    private EditText editTextItem;
    private EditText editTextQuantity;

    private CharSequence editItem;
    private CharSequence editQuantity;

    private BottomSheetListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);

        editTextItem = view.findViewById(R.id.editTextItem);
        editTextQuantity = view.findViewById(R.id.editTextQuantity);
        Button buttonInsert = view.findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editItem = editTextItem.getText();
                editQuantity = editTextQuantity.getText();
                Toast.makeText(v.getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
                listener.onButtonClick(editItem, editQuantity);
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must Implement BottomSheetListener");
        }
    }

    public interface BottomSheetListener {
        void onButtonClick(CharSequence editText, CharSequence editQuantity);

    }
}
