package com.dicoding.picodiploma.eatnowcapstone.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.eatnowcapstone.ChangePasswordActivity
import com.dicoding.picodiploma.eatnowcapstone.EditBmiActivity
import com.dicoding.picodiploma.eatnowcapstone.SignInActivity
import com.dicoding.picodiploma.eatnowcapstone.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val linearLayoutEdit: LinearLayout = binding.linearLayoutEdit

        linearLayoutEdit.setOnClickListener {
            val intent = Intent(requireContext(), EditBmiActivity::class.java)
            startActivity(intent)
        }
        val linearLayoutChange : LinearLayout = binding.linearLayoutgp

        linearLayoutChange.setOnClickListener {
            val intent = Intent(requireContext(), ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        val logoutLayout : LinearLayout = binding.logoutLayout

        logoutLayout.setOnClickListener {
            val intentLogout = Intent(requireContext(), SignInActivity::class.java)
            intentLogout.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intentLogout)
            requireActivity().finish()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
