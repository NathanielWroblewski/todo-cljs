(ns table.component
  (:require [reagent.core :as reagent :refer [atom]]
            [row.component :as row]
            [input.component :as input]))

(def todos (atom []))

(defn add-todo [subject]
  (swap! todos conj {:subject subject :complete false}))

(defn delete [todo]
  (let [subject (:subject todo) complete (:complete todo)]
    (swap! todos (fn []
      (into [] (remove (fn [item]
        (and (= (:subject item) subject) (= (:complete item) complete)))
      @todos))))))

(defn complete [todo]
  (delete todo)
  (swap! todos conj {:subject (:subject todo) :complete true}))

(defn render []
  [:div.table
    [:div.binding]
    [:div.content
      (input/render {:on-submit add-todo})
      (map #(row/render {:todo % :complete complete :delete delete}) @todos)]])
