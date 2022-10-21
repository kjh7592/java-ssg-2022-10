package com.kor.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kor.java.ssg.dto.Article;
import com.kor.java.ssg.util.Util;

public class App {

	private List<Article> articles;
	
	public App() {
		articles = new ArrayList<>();
	}
	
	public void start() {
		System.out.println("===프로그램 시작===");
		
		makeTestArticles();

		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.printf("명령어)");
			String command = sc.nextLine();
			
			command = command.trim();
			
			if(command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			if(command.equals("system exit")) {
				break;
			}
			else if(command.equals("article write")) {
				int id = articles.size() + 1;
				String regDate = Util.getNowDateStr();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(id, regDate, title, body);
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			}
			else if(command.equals("article list")) {
				if(articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				
				System.out.println("번호	|	조회	|	제목");
				for(int i = articles.size() - 1; i >= 0 ; i--) {
					Article article = articles.get(i);
					
					System.out.printf("%d	|	%d	|	%s\n", article.id, article.hit, article.title);
				}
			}
			
			else if(command.startsWith("article detail ")) {
				
				String commandBits[] = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				Article foundArticle = getArticleById(id);
				
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				foundArticle.incresehit();
				
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("날짜 : " + foundArticle.regDate);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				System.out.println("조회 : " + foundArticle.hit);
			}
			else if(command.startsWith("article modify ")) {
				
				String commandBits[] = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				Article foundArticle = getArticleById(id);
				
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();
				
				foundArticle.title = title;
				foundArticle.body = body;
				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
			}
			
			else if(command.startsWith("article delete ")) {
				
				String commandBits[] = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				int foundindex = getArticleIndexById(id);
				
				if(foundindex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				} 
				articles.remove(foundindex);
				System.out.printf("%d 게시물이 삭제되었습니다.\n", id);
			}
			
			else {
				System.out.printf("%s(은)는 존재하지 않는 명령어입니다.\n", command);
			}
		}
		
		

		sc.close();

		System.out.println("===프로그램 끝===");
	}


	private void makeTestArticles() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
		
		articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 10));
		articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), "제목3", "내용3", 33));
	}


	private Article getArticleById(int id) {
		
		int index = getArticleIndexById(id);
		
		if(index != -1) {
			
			return articles.get(index);
		}
		return null;
	}

	private int getArticleIndexById(int id) {
		
		for(int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			
			if(id == article.id) {
				return i;
			}
		}
		return -1;
	}
}
