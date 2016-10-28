(ns segtree.core-test
  (:require [segtree.core :refer :all]
            [clojure.test :refer :all]))


(defmethod join-vals :sum [a b _] (+ a b))
(defmethod identity-val :sum [_] 0)
(defmethod join-range-val :sum
  [{:keys [idx-left idx-right] :as tree} v]
  (update tree :value join-vals (* (inc (- idx-right idx-left)) v) :sum))

(deftest query-sum-test
  (let [arr [1 2 3 4 5 6 7 8 9 10]
        st  (build arr :sum)]
    (testing "Query returns expected results"
      (are [a b] (= (query st a b) (apply + (subvec arr a (inc b))))
        0 9
        0 4
        3 9
        2 6))
    (testing "Out of bounds queries return identity val"
      (is (= (query st 15 20)
             (identity-val st))))))


(defn tree->arr [tree]
  (for [i (range (:idx-left tree) (inc (:idx-right tree)))]
    (query tree i i)))

(deftest point-update-test
  (let [arr [1 2 3 4 5 6 7 8 9 10]
        st (build arr :sum)]
    (testing "point updates behave properly"
      (is (= (-> st (point-update 3 4) tree->arr)
             [1 2 3 8 5 6 7 8 9 10]))
      (is (= (-> st (point-update 3 2) (point-update 0 4) (point-update 9 1) tree->arr)
             [5 2 3 6 5 6 7 8 9 11])))

    (testing "updates outside tree range keeps tree unmodified"
      (is (= (tree->arr (point-update st 10 1))
             [1 2 3 4 5 6 7 8 9 10])))))

(defmethod join-vals :sum-no-range-update [a b _] (+ a b))
(defmethod identity-val :sum-no-range-update [_] 0)


(deftest range-update-test
  (let [arr [1 2 3 4]
        st-no-range-update (build arr :sum-no-range-update)
        st (build arr :sum)]
    (testing "returns `nil` if `join-range-val` is not defined for the tree type"
      (is (= (range-update st-no-range-update 0 3 1)
             nil)))

    (testing "updates range accordingly"
      (is (= (-> st (range-update 0 3 1) tree->arr)
             [2 3 4 5])))))
