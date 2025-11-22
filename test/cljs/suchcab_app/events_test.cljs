(ns suchcab-app.events-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [suchcab-app.events :as events]))

(deftest validate-email-test
  (testing "Valid email addresses"
    (is (true? (events/validate-email "user@example.com")))
    (is (true? (events/validate-email "test.user@domain.co.uk")))
    (is (true? (events/validate-email "firstname+lastname@example.com"))))

  (testing "Invalid email addresses"
    (is (false? (events/validate-email "")))
    (is (false? (events/validate-email "not-an-email")))
    (is (false? (events/validate-email "@example.com")))
    (is (false? (events/validate-email "user@")))
    (is (false? (events/validate-email nil)))))

(deftest validate-password-test
  (testing "Valid passwords"
    (is (true? (events/validate-password "123456")))
    (is (true? (events/validate-password "password123")))
    (is (true? (events/validate-password "verylongpassword"))))

  (testing "Invalid passwords"
    (is (false? (events/validate-password "")))
    (is (false? (events/validate-password "12345")))
    (is (false? (events/validate-password "short")))
    (is (false? (events/validate-password nil)))))

(deftest validate-credentials-test
  (testing "Valid credentials return no errors"
    (is (empty? (events/validate-credentials "user@example.com" "password123"))))

  (testing "Invalid email returns error"
    (let [errors (events/validate-credentials "bad-email" "password123")]
      (is (contains? errors :email))
      (is (= "Invalid email format" (:email errors)))))

  (testing "Invalid password returns error"
    (let [errors (events/validate-credentials "user@example.com" "short")]
      (is (contains? errors :password))
      (is (= "Password must be at least 6 characters" (:password errors)))))

  (testing "Both invalid returns both errors"
    (let [errors (events/validate-credentials "bad-email" "short")]
      (is (contains? errors :email))
      (is (contains? errors :password)))))
