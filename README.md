circuit-sdk-android-demo
===================

Android向けCircuit SDK用サンプルアプリのリポジトリです。

ECサイトの振舞いを模したアプリであり、
Circuit SDKの導入前/導入後の状態をブランチ分けして用意しています。
それらのコードをご覧いただき、Circuit SDK導入の参考にご利用ください。

## ブランチ
* master     :Circuit SDKを未導入状態のブランチとなります。
* use-circuit:Circuit SDKにてCircuitディープリンクサポーターを導入済みのブランチとなります。
* use-cdr    :Circuit SDKにてCircuitDirectを導入済みのブランチとなります。

## Circuitディープリンクサポーター

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

#### 導入手順詳細ドキュメント
* https://developer.cir.io/doc/cds

## CircuitDirect

#### 動作確認手順
* 本リポジトリをローカルにcloneし、use-cdrブランチにcheckoutする
```
$ git clone https://github.com/FukurouLabo/circuit-sdk-android-deeplink-demo.git
$ cd circuit-sdk-android-deeplink-demo
$ git checkout -b use-cdr origin/use-cdr
```
* AndroidStudioを起動し、circuit-sdk-android-deeplink-demoプロジェクトを開く。
* アプリをビルドし、端末上からアプリを一度削除してからadbコマンドにてアプリを再インストールし、後述のダイレクトリンクからアプリを初回起動する。

#### 設定済みダイレクトリンク
* 商品詳細ページ:https://dr.cir.io/ur/Qked4Q?item_id={商品番号(1 ~ 3)}

#### 導入手順詳細ドキュメント
* https://developer.cir.io/doc/circuit-direct


## Circuitサイト
* http://www.cir.io/

#### Licence
The source code is released under Apache 2.0.
