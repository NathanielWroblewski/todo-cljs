(ns row.component
  (:require [reagent.core :as reagent :refer [atom]]))

(defn render [props]
  (let [todo (:todo props) complete (:complete props) delete (:delete props)]
    [:div.todo
      (if (:complete todo)
        [:div
          [:span.check.complete "✓ "]
          [:span.subject.finished (:subject todo)]]
        [:div {:on-click #(complete todo)}
          [:span.check.incomplete "✓ "]
          [:span.subject (:subject todo)]])
      [:span.delete {:on-click #(delete todo)} "❌"]]))
