(ns input.component
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure.string :as string :refer [blank?]]))

(def todo (atom ""))

(defn update-state [event]
  (swap! todo #(str event.target.value)))

(defn reset []
  (swap! todo #(str "")))

(defn capture-submit [event on-submit]
  (when (and (= event.key "Enter") (not (blank? event.target.value)))
    (and (on-submit event.target.value) (reset))))

(defn render [props]
  [:div.new
    [:input.field {
      :type "text"
      :value @todo
      :on-key-press #(capture-submit % (:on-submit props))
      :on-change update-state
      :placeholder "What do you have to do?"}]])
