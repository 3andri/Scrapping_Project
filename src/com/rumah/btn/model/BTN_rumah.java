package com.rumah.btn.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BTN_rumah {
	@Id
	private String btn_url;
	@Column
	private String btn_gambar_url;
	@Column
	private String btn_gambar_local;
	@Column
	private String btn_kode_aset;
	@Column
	private String btn_dokumen;
	@Column
	private BigInteger btn_harga;
	@Column
	private BigInteger btn_harga_awal;
	@Column(length = 1000)
	private String btn_lokasi;
	@Column
	private int btn_lebar_jalan_depan;
	@Column
	private int btn_luas_tanah;
	@Column
	private int btn_luas_bangunan;
	@Column(length = 1000)
	private String btn_rencana_lokasi_penjualan;
	@Column
	private String btn_keterangan_penjualan;
	@Column
	private String btn_nomor_telp;
	@Column
	private boolean btn_view_or_not;
}
