package com.nvt.bloodbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nvt.bloodbank.adapters.CertAdapter
import com.nvt.bloodbank.databinding.DonatedCertificateBinding
import com.nvt.bloodbank.dto.Certificates

class DonatedCert:Fragment() {
    private lateinit var binding : DonatedCertificateBinding
    private lateinit var adapter: CertAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DonatedCertificateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CertAdapter()
        adapter.submitList(listOf(Certificates(0,null,null,0,null)))
        val lm = LinearLayoutManager(context)
        binding.listCert.adapter = adapter
        binding.listCert.layoutManager = lm
    }
}