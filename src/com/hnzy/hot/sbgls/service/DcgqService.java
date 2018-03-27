package com.hnzy.hot.sbgls.service;

import java.util.List;

import com.hnzy.hot.sbgls.pojo.Dcgq;
import com.sun.org.apache.bcel.internal.generic.UnconditionalBranch;

public interface DcgqService
{
  public List<Dcgq> find();
  public void delete(int id);
}
