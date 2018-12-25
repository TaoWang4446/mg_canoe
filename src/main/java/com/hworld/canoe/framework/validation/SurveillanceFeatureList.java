package com.hworld.canoe.framework.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents multiple SurveillanceFeature while multiple exceptions
 * occur. It is useful while there are multiple exception occurs and all the
 * messages need to be displayed on the screen view to the user.
 * @author Raymond Lei Chen
 */
public class SurveillanceFeatureList implements Serializable,
    Iterable<SurveillanceFeature> {
  /**
   * 
   */
  private static final long serialVersionUID = -2520298657912624505L;

  /**
   * Internal list object
   */
  protected List<SurveillanceFeature> sfList = new ArrayList<SurveillanceFeature>();

  /**
   * Constructor
   */
  public SurveillanceFeatureList() {
  }

  /**
   * Constructor
   * @param list List
   */
  public SurveillanceFeatureList(List<SurveillanceFeature> list) {
    if (list != null) {
      sfList = list;
    }
  }

  /**
   * Adds SurveillanceFeature object into list
   * @param sf SurveillanceFeature
   */
  public void add(SurveillanceFeature sf) {
    if (sf == null) {
      sfList.add(new SurveillanceFeature());
    } else {
      sfList.add(sf);
    }
  }

  /**
   * Inserts the specified element into this list at the specified position.
   * @param index - index at which to insert the first specified
   * @param sf SurveillanceFeature - the element to be added to this list
   */
  public void add(int index, SurveillanceFeature sf) {
    if (sf == null) {
      sfList.add(index, new SurveillanceFeature());
    } else {
      sfList.add(index, sf);
    }
  }

  /**
   * Appends all of the elements in the specified collection to the end of this
   * list, in the order that they are returned by the specified collection's
   * iterator. The behavior of this operation is undefined if the specified
   * collection is modified while the operation is in progress. (Note that this
   * will occur if the specified collection is this list, and it's nonempty.)
   * @param sflst SurveillanceFeatureList - collection containing elements to be
   *        added to this list
   */
  public void addAll(SurveillanceFeatureList sflst) {
    if (sflst == null) {
      //
    } else {
      for (SurveillanceFeature sf : sflst) {
        add(sf);
      }
    }
  }

  /**
   * Inserts all of the elements in the specified collection into this list at
   * the specified position. Shifts the element currently at that position (if
   * any) and any subsequent elements to the right (increases their indices).
   * The new elements will appear in this list in the order that they are
   * returned by the specified collection's iterator. The behavior of this
   * operation is undefined if the specified collection is modified while the
   * operation is in progress. (Note that this will occur if the specified
   * collection is this list, and it's nonempty.)
   * @param index - index at which to insert the first element from the
   *        specified collection
   * @param sflst SurveillanceFeatureList - collection containing elements to be
   *        added to this list
   */
  public void addAll(int index, SurveillanceFeatureList sflst) {
    if (sflst == null) {
      //
    } else {
      for (int i = index, j = 0; i <= (sflst.size() + index)
          && j < sflst.size(); i++, j++) {
        add(i, sflst.get(j));
      }
    }
  }

  /**
   * Gets SurveillanceFeature object from list
   * @param index int
   * @return SurveillanceFeature
   */
  public SurveillanceFeature get(int index) {
    return sfList.get(index);
  }

  /**
   * Returns size
   * @return int
   */
  public int size() {
    return sfList.size();
  }

  /**
   * Returns Iterator of the list
   * @return Iterator
   */
  @Override
  public Iterator<SurveillanceFeature> iterator() {
    return sfList.iterator();
  }

  /**
   * Convert to Array
   * @return SurveillanceFeature[]
   */
  public SurveillanceFeature[] toArray() {
    return (SurveillanceFeature[]) sfList
        .toArray(new SurveillanceFeature[size()]);
  }

  /**
   * Gets surveillance id as string array.
   * @return String[]
   */
  public String[] getSurvIds() {
    String[] survIds = new String[size()];
    for (int i = 0; i < survIds.length; i++) {
      SurveillanceFeature sf = get(i);
      survIds[i] = sf.getSurvId();
    }
    return survIds;
  }

  /**
   * Returns List object
   * @return List
   */
  public List<SurveillanceFeature> getAll() {
    return sfList;
  }

  /**
   * In most cases there is only one element in the list, and therefore this
   * method is for convenience purpose.
   * @return String
   */
  public String getWarnMsgId() {
    if (sfList != null && sfList.size() > 0) {
      SurveillanceFeature sf = sfList.get(0);
      String msgid = sf.getWarnMsgId();
      return msgid;
    } else {
      return null;
    }
  }

  /**
   * Returns the text name of the HTML element to be set focus.
   * @return the focusName
   */
  public List<String> getFocusName() {
    List<String> name = new ArrayList<String>();
    if (sfList != null && sfList.size() > 0) {
      for (int i = 0; i < sfList.size(); i++) {
        SurveillanceFeature sf = sfList.get(i);
        name.add(i, sf.getFocusName());
      }
    } else {
      return null;
    }
    return name;
  }

  /**
   * Returns the index of the HTML element to be set focus.
   * @return the focusIndex
   */
  public List<Integer> getFocusIndex() {
    List<Integer> index = new ArrayList<Integer>();
    if (sfList != null && sfList.size() > 0) {
      for (int i = 0; i < sfList.size(); i++) {
        SurveillanceFeature sf = sfList.get(i);
        index.add(i, sf.getFocusIndex());
      }
    } else {
      return null;
    }
    return index;
  }

  /**
   * Clears contents
   */
  public void clear() {
    sfList.clear();
  }
}
