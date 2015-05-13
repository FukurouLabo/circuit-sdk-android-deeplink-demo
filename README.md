circuit-sdk-android-demo
===================

Android向けCircuit SDK用サンプルアプリのリポジトリです。

ECサイトの振舞いを模したアプリであり、
Circuit SDKの導入前/導入後の状態をブランチ分けして用意しています。
それらのコードをご覧いただき、Circuit SDK導入の参考にご利用ください。

#### ブランチ
* master     :Circuit SDKを未導入状態のブランチとなります。
* use-circuit:Circuit SDKを導入済み状態のブランチとなります。

#### 動作確認手順
* 本リポジトリをローカルにcloneし、use-circuitブランチにcheckoutする
```
$ git clone https://github.com/FukurouLabo/circuit-sdk-android-deeplink-demo.git
$ cd circuit-sdk-android-deeplink-demo
$ git checkout -b use-circuit origin/use-circuit
```
* AndroidStudioを起動し、circuit-sdk-android-deeplink-demoプロジェクトを開く。
* エミュレータあるいはAndroid端末実機にビルドする。

#### 設定済みディープリンク
* 商品詳細ページ:circuitdemo://deepLinkItemId/{商品番号(1 ~ 3)}

#### Circuitサイト
* トップページ :http://www.cir.io/
* ドキュメント :https://developer.cir.io/doc/cds
* FAQ :https//developer.cir.io/faq/cds
