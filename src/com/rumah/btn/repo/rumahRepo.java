package com.rumah.btn.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rumah.btn.model.BTN_rumah;

@Repository
public interface rumahRepo extends CrudRepository<BTN_rumah, String> {
	@Query("SELECT r FROM BTN_rumah r WHERE LOWER(BTN_LOKASI) LIKE %:kota% AND BTN_VIEW_OR_NOT=:view  ORDER BY BTN_HARGA ")
	public List<BTN_rumah> Custom(@Param("kota") String kota, @Param("view") boolean view);

}
