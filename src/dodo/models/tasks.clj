(ns dodo.models.tasks
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as s]))

(def db {:classname "org.sqlite.JDBC"
         :subprotocol "sqlite"
         :subname "db/database.db"})

(j/insert! db :tasks
             {:id "23434567"
              :tasker_id "434"
              :doer_id "56456"
              :title "uberite"
              :description "i vernite"
              :updated_at "25-05-2015"
              :created_at "21-05-2015"
              :cat_id "1"})

(defn all [] (j/query db (s/select * :tasks)))

(defn tasker [id] (j/query db (s/select * :tasks (s/where {:tasker_id id}))))

;(all)
;(tasker 434)
