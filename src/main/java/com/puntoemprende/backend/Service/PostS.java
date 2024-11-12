/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Service;

import Exceptions.CustomException;
import com.puntoemprende.backend.Model.Category;
import com.puntoemprende.backend.Model.Motive;
import com.puntoemprende.backend.Model.Permission;
import com.puntoemprende.backend.Model.Post;
import com.puntoemprende.backend.Model.ReportedStatus;
import com.puntoemprende.backend.Model.Scope;
import com.puntoemprende.backend.Model.User;
import com.puntoemprende.backend.Repository.PermissionR;
import com.puntoemprende.backend.Repository.PostR;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruisu's
 */
@Service
public class PostS {
    
    
    @Autowired
    private PostR postR;
    
    @Autowired
    private UserS userS;
    
    @Autowired
    private ReportedStatusS reportedStatusS;
    
    @Autowired
    private ScopeS scopeS;
    
    @Autowired
    private CategoryS categoryS;
    
    @Autowired
    private MotiveS motiveS;
    
    public List<Post> getAll(){
        return postR.getAll();
    }
    
    public Post getById(Integer id){
        Post post = postR.getById(id).orElseThrow(()-> new CustomException("No se encontró la publicación"));
        return post;
    }
    
    public Post createPost(Post post){
        userS.getUser(post.getUser().getId());
        reportedStatusS.getById(post.getReportedStatus().getId());
        scopeS.getById(post.getScope().getId());
        categoryS.getById(post.getCategory().getId());
        motiveS.getById(post.getMotive().getId());
        return postR.createPost(post);
    }
    
    public Post updatePost(Post post){
        Post postDb = postR.getById(post.getId()).orElseThrow(()-> new CustomException("No se encontro la publicación"));
        if(post.getTitle() != null) {postDb.setTitle(post.getTitle());}
        if(post.getPrice() > 0) {postDb.setPrice(post.getPrice());}
        if(post.getCant() > 0) {postDb.setCant(post.getCant());}
        if(post.getDescription() != null) {postDb.setDescription(post.getDescription());}
        if(post.getImage()!= null) {postDb.setImage(post.getImage());}
        if(post.getHaveSend()!= null) {postDb.setHaveSend(post.getHaveSend());}
        if(post.getUser()!= null && post.getUser().getId() != null) {
            User userDb = userS.getUser(post.getUser().getId());
            postDb.setUser(userDb);
        }
        if(post.getReportedStatus()!= null && post.getReportedStatus().getId() != null) {
            ReportedStatus reportedStatusDb = reportedStatusS.getById(post.getReportedStatus().getId());
            postDb.setReportedStatus(reportedStatusDb);
        }
        if(post.getScope()!= null && post.getScope().getId() != null) {
            Scope scopeDb = scopeS.getById(post.getScope().getId());
            postDb.setScope(scopeDb);
        }
        if(post.getCategory()!= null && post.getCategory().getId() != null) {
            Category categoryDb = categoryS.getById(post.getCategory().getId());
            postDb.setCategory(categoryDb);
        }
        if(post.getMotive()!= null && post.getMotive().getId() != null) {
            Motive motiveDb = motiveS.getById(post.getMotive().getId());
            postDb.setMotive(motiveDb);
        }
        
        return postR.updatePost(postDb);
    }
    
    public void deletePost(Integer id){
        postR.getById(id).orElseThrow(()-> new CustomException("No se encontro la publicación"));
        postR.deletePost(id);
    }
    
    public List<Post> findByTitleOrDescription(String input){
        return postR.findByTitleOrDescription(input);
    }
   
    public List<Post> findByCategoryId(int id){
        categoryS.getById(id);
        return postR.findByCategoryID(id);
    }
    
    public List<Post> findByScopeId(int id){
        scopeS.getById(id);
        return postR.findByScopeID(id);
    }
    
}
