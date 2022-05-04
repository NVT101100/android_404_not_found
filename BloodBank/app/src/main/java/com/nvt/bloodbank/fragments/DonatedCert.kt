package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nvt.bloodbank.adapters.CertAdapter
import com.nvt.bloodbank.databinding.DonatedCertificateBinding
import com.nvt.bloodbank.models.DonatedCertModel

class DonatedCert:Fragment() {
    private lateinit var binding : DonatedCertificateBinding
    private lateinit var adapter: CertAdapter
    private lateinit var model : DonatedCertModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DonatedCertificateBinding.inflate(inflater,container,false)
        model = DonatedCertModel()
        adapter = CertAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.init()
        model.listCert.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            val lm = LinearLayoutManager(context)
            binding.listCert.adapter = adapter
            binding.listCert.layoutManager = lm
        }
        model.user.observe(viewLifecycleOwner) {
            binding.certName.text = it.name
        }
    }
}
