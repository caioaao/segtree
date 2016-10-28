(ns segtree.core-test
  (:require [segtree.core :refer :all]
            [clojure.test :refer :all]))


(defmethod join-vals :sum [a b _] (+ a b))

(defmethod identity-val :sum [_] 0)

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
      (is (query st 15 20) (identity-val st)))))


(defn tree->arr [tree]
  (for [i (range (:idx-left tree) (:idx-right tree))]
    (query tree i i)))

(deftest point-update-test
  (let [arr [1 2 3 4 5 6 7 8 9 10]
        st (build arr :sum)]
    (testing "point updates behave properly"
      (is (-> st (point-update 3 4) tree->arr)) [1 2 3 8 5 6 7 8 9 10]
      (is (-> st (point-update 3 2) (point-update 0 4) (point-update 9 1) tree->arr)) [5 2 3 6 5 6 7 8 9 11])

    (testing "updates outside tree range keeps tree unmodified"
      (is (tree->arr (point-update st 10 1)) [1 2 3 4 5 6 7 8 9 10]))))
