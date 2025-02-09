package couponfinder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 *PS:  Given the following set of data sets, create a function that will find the coupon to display for a given category.

Coupons = [
{"CategoryName:Comforter Sets", "CouponName:Comforters Sale"},
{"CategoryName:Bedding", "CouponName:Savings on Bedding"},
{"CategoryName:Bed & Bath", "CouponName:Low price for Bed & Bath"}
]

Categories = [
{"CategoryName:Comforter Sets", "CategoryParentName:Bedding"},
{"CategoryName:Bedding", "CategoryParentName:Bed & Bath"},
{"CategoryName:Bed & Bath", "CategoryParentName:None"},
{"CategoryName:Soap Dispensers", "CategoryParentName:Bathroom Accessories"},
{"CategoryName:Bathroom Accessories", "CategoryParentName:Bed & Bath"},
{"CategoryName:Toy Organizers", "CategoryParentName:Baby And Kids"},
{"CategoryName:Baby And Kids", "CategoryParentName:None}
]
Requirements/Acceptance Criteria:

Create a function that when passed a Category Name (as a String) will return Coupon Name (as a String)
Category structure is hierarchical. Categories without coupons inherit their parent’s coupon.
No coupon should be returned if there are no coupons in the Category’s hierarchy
For example: Toy Organizers receives no coupon because there is no coupon in the category hierarchy.
If a Category has a coupon it should not move up the hierarchy to find its Parent Category (or the Parent’s Coupon)
For example: Comforter sets, should see the coupon for Comforter Sets and NOT Bedding
 */

@Slf4j
@Setter
@Getter
public class CouponManager {
    HashMap<String, String> categoryCouponMap;
    HashMap<String, List<String>> categoryTree;
    private static final String END_CATEGORY = "CategoryName:None";

    public CouponManager(){
        this.categoryCouponMap = new HashMap<>();
        this.categoryTree = new HashMap<>();
    }

    String getAvailableCoupon(String categoryName){
        final String[] availableCoupon = {null};
        List<String> availableCategoryTree = this.categoryTree.get(categoryName);
        if(this.categoryCouponMap.containsKey(categoryName)){
            return this.categoryCouponMap.get(categoryName);
        }
        else{
            AtomicBoolean stop = new AtomicBoolean(false);
            availableCategoryTree.forEach((cat) -> {
                if(!stop.get() && this.categoryCouponMap.containsKey(cat)){
                    stop.set(true);
                    availableCoupon[0] = this.categoryCouponMap.get(cat);
                }
            });
        }
        return availableCoupon[0];
    }

    List<String> getRecCategoryTree(HashMap<String, String> childToParentMap, String parent, List<String> parentListSoFar){
        if(parent.equals(END_CATEGORY)){
            return parentListSoFar;
        }
        parentListSoFar.add(parent);
        if(this.categoryTree.containsKey(parent)){
            parentListSoFar.addAll(this.categoryTree.get(parent));
            return parentListSoFar;
        }
        else{
            String parentOfParent = null;
            if(childToParentMap.containsKey(parent)){
                parentOfParent = childToParentMap.get(parent);
                if(parentOfParent.equals(END_CATEGORY)){
                    return parentListSoFar;
                }
                else{
                    return getRecCategoryTree(childToParentMap, parentOfParent, parentListSoFar);
                }
            }
            else{
                return parentListSoFar;
            }
        }
    }

    public void createCategoryTree(HashMap<String, String> childToParentMap){
        childToParentMap.forEach((k, v) -> {
            // check if parent category has a category hierarchy
            List<String> currentCategoryHierarchy = new ArrayList<>();
            currentCategoryHierarchy = getRecCategoryTree(childToParentMap, v, currentCategoryHierarchy);
            this.categoryTree.put(k, currentCategoryHierarchy);
        });
    }

    public static void main(String[] args) {
        CouponManager couponManager = new CouponManager();
        couponManager.categoryCouponMap.put("CategoryName:Comforter Sets", "CouponName:Comforters Sale");
        couponManager.categoryCouponMap.put("CategoryName:Bedding", "CouponName:Savings on Bedding");
        couponManager.categoryCouponMap.put("CategoryName:Bed & Bath", "CouponName:Low price for Bed & Bath");
        HashMap<String, String> childToParentMap = new HashMap<>();
        childToParentMap.put("CategoryName:Comforter Sets", "CategoryName:Bedding");
        childToParentMap.put("CategoryName:Bedding", "CategoryName:Bed & Bath");
        childToParentMap.put("CategoryName:Bed & Bath", "CategoryName:None");
        childToParentMap.put("CategoryName:Soap Dispensers", "CategoryName:Bathroom Accessories");
        childToParentMap.put("CategoryName:Bathroom Accessories", "CategoryName:Bed & Bath");
        childToParentMap.put("CategoryName:Toy Organizers", "CategoryName:Baby And Kids");
        childToParentMap.put("CategoryName:Baby And Kids", "CategoryName:None");
        couponManager.createCategoryTree(childToParentMap);
        //System.out.println(couponManager.getCategoryTree());
        System.out.println(couponManager.getAvailableCoupon("CategoryName:Comforter Sets"));
        System.out.println(couponManager.getAvailableCoupon("CategoryName:Bathroom Accessories"));
    }
}
