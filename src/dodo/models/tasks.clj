(ns dodo.models.tasks
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as s]))

(def db {:classname "org.sqlite.JDBC"
         :subprotocol "sqlite"
         :subname "db/database.db"})

(j/insert! db :tasks
             {:id 1
              :tasker_id "32"
              :doer_id "12"
              :title "uvezite"
              :description "v les"
              :updated_at "4-06-2015"
              :created_at "1-06-2015"
              :cat_id "5"})

(defn all [] (j/query db (s/select * :tasks)))

(defn tasker [id] (j/query db (s/select * :tasks (s/where {:tasker_id id}))))

(all)
;(tasker 434)
