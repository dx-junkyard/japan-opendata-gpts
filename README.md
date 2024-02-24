<div align="center">
    <img src="https://japan-opendata-chatgpt-plugin.s3.amazonaws.com/logo.png" height="200px" width="200px"/>
    <br />
    <h3>Japan OpenData for GPTs</h3>
    <br />
    <p><a href="https://search.ckan.jp/">データカタログ横断検索システム</a>に掲載されているオープンデータを検索できるGPTsです</p>
</div>

## GPTs とは？

GPTs は、ChatGPT に新しい機能を追加したり、既存機能を強化したりするための拡張ツールです。  
詳しくは下記の公式ドキュメントをご確認ください。

- [Actions in GPTs](https://platform.openai.com/docs/actions/introduction)

## Japan OpenData for GPTs とは？

Japan OpenData for GPTs は、<a href="https://search.ckan.jp/">データカタログ横断検索システム</a>に掲載されているオープンデータを ChatGPT から検索したり、データを取得したりする GPTs です。

本 GPTs を利用することで、ChatGPT と対話しながらオープンデータの検索ができるようになります。

## 開発に参加するためには？

Japan OpenData for GPTs の開発に興味を持っていただき、ありがとうございます。  
より良いプラグインの実現のため、機能改善やバグ修正の参加を募集しています。

皆さんが開発に参加するために、一連の開発の流れの例を記載します。

### 事前知識

本プロジェクトは、GitHub flow に基づいて開発を行います。
[GitHub flow - GitHub Docs](https://docs.github.com/en/get-started/using-github/github-flow)

原則、main ブランチに向けて Pull Request を作成してください。  
main ブランチにマージされたコードは、リリースのタイミングで main ブランチにマージされます。

main ブランチにマージされたコードは、CODEOWNERS によってリリースパッケージが作成された後に、本番環境にデプロイされます。

### 開発の流れ

#### 1. issue の作成

機能改善の要望やバグの報告があれば、まずは報告をお願いします。

#### 2. リポジトリのフォーク

GitHub のリポジトリページで本プロジェクトをフォークしてください。

#### 3. プロダクトコードの修正

作成した issue に関して、プロダクトコードに変更を加えましょう。

#### 4. 変更したコードの push

変更したコードを push しましょう。  
ブランチ名は「feature/issue-{IssueID}」のような命名規則としてください。

#### 5. PR の作成

main ブランチに向けて PR を作成しましょう。CODEOWNERS のメンバーがレビューします。

#### 6. プルリクエストのレビュー

プルリクエストがレビューされ、問題がなければ本プロジェクトにマージされます。  
CODEOWNERS から修正の指摘があった場合は、それに応じて変更を加えてください。

## 開発準備

事前に Java のインストールをお願いします。

```
$ git clone https://github.com/<your_account>/japan-opendata-gpts.git
$ cd japan-opendata-gpts.git
$ ./gradlew clean bootJar
$ java -jar build/libs/japan-opendata-gpts.jar
```

## 参考資料

開発に入る前に以下の資料の確認をおすすめします。

1. GPTs の開発方法のドキュメント  
   GPTs の開発の上で必要な情報が記載されています。  
   [Actions in GPTs](https://platform.openai.com/docs/actions/introduction)

2. CKAN のドキュメント  
   オープンデータを取得する API の機能について記載されています。  
   [API guide — CKAN 2.8.12 documentation](https://docs.ckan.org/en/2.8/api/)
