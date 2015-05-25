(ns dodo.models.tasks
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as s]))

(def db {:classname "org.sqlite.JDBC"
         :subprotocol "sqlite"
         :subname "db/database.db"})

(j/insert! db :tasks
             {:id "234"
              :tasker_id "23434"
              :doer_id "56456"
              :title "pozvonite"
              :description "i vernite"
              :updated_at "23-05-2015"
              :created_at "22-05-2015"
              :cat_id "2"})

(defn all [] (j/query db (s/select * :tasks)))

(all)
