package com.andy.Medicab.dao;

import com.andy.Medicab.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileInfoDao extends JpaRepository<FileInfo,Long> {
    FileInfo getFileInfoByNameAllIgnoreCase(String nAME);
}
