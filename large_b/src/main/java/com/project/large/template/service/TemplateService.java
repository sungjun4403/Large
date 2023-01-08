package com.project.large.template.service;

import com.project.large.template.dto.TemplateCreate;
import com.project.large.template.dto.TemplateEdit;
import com.project.large.template.dto.TemplateEditor;
import com.project.large.template.entity.Template;
import com.project.large.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;

    //CREATE
    public void create (TemplateCreate templateCreate) {

        Template template = Template.builder()
                .gitID(templateCreate.getGitID())
                .templateName(templateCreate.getTemplateName())
                .templateNumber(templateCreate.getTemplateNumber())
                .template(templateCreate.getTemplate())
                .hotKey(templateCreate.getHotKey())
                .build();

        templateRepository.save(template);
    }

    //EDIT
    @Transactional
    public void edit (TemplateEdit templateEdit, Long templateID) {
        Template template = templateRepository.findById(templateID).orElseThrow();

        TemplateEditor.TemplateEditorBuilder editorBuilder = template.toEditor();

        editorBuilder.template(templateEdit.getGitID()); //in editable

        if (templateEdit.getTemplateName() != null) {
            editorBuilder.templateName(templateEdit.getTemplateName());
        }
        if (templateEdit.getTemplateNumber() != null) {
            editorBuilder.templateNumber(templateEdit.getTemplateNumber());
        }
        if (templateEdit.getTemplate() != null) {
            editorBuilder.template(templateEdit.getTemplate());
        }
        if (templateEdit.getHotKey() != null) {
            editorBuilder.hotKey(templateEdit.getHotKey());
        }

        template.edit(editorBuilder.build());
    }


    //DELETE
    public void delete (Long id) {
        Template template = templateRepository.findById(id).orElseThrow();
        templateRepository.delete(template);
    }

    //Divide Added Template from wholeTemplate
    public void divideCreateFromWhole(List<LinkedHashMap<Object, Object>> wholeTemplate, String gitID) {
        List<LinkedHashMap<Object, Object>> modifiedNDeleted = new ArrayList<>();
        List<LinkedHashMap<Object, Object>> created = new ArrayList<>();

        wholeTemplate.forEach(template-> {
            System.out.println(template);
            String templateId = template.get("id").toString();
            if (IntAble(templateId)) {
                modifiedNDeleted.add(template);
            }
            else {
                created.add(template);
            }
        });

        modifyNDelete(modifiedNDeleted, gitID);
        createFromCreated(created);
        //Create

    }

    private void createFromCreated(List<LinkedHashMap<Object, Object>> created) {
        created.forEach(template -> {
            if (template.get("template").toString().isEmpty() || template.get("template").toString().isBlank()) {
                return;
            }
            else {
                Template toSave = Template.builder()
                        .gitID((String) template.get("gitID"))
                        .templateName((String) template.get("templateName"))
                        .templateNumber(Integer.parseInt(template.get("templateNumber").toString()))
                        .template((String) template.get("template"))
                        .hotKey((String) template.get("hotKey"))
                        .build();
                templateRepository.save(toSave);
            }
        });
    }

    public void modifyNDelete(List<LinkedHashMap<Object, Object>> modifiedNDeleted, String gitID) {
        List<Template> lastlySavedTemplate = templateRepository.findByGitID(gitID);
        List<Long> lastlySavedId = new ArrayList<>();
        List<Long> MNDId = new ArrayList<>();
        //Edit or Not
        List<Long> toModify = new ArrayList<>();
        //Edit
        List<Long> toEdit= new ArrayList<>();
        List<Long> toDelete= new ArrayList<>();

        //온게 없는건 다 지웠다는거니까
        if (modifiedNDeleted.size() == 0) {
            lastlySavedTemplate.forEach(toDeleteTemplate -> {
                delete(toDeleteTemplate.getId());
            });
        }
        else {
            // {(id)=(MND), ...}
            Map<Long, LinkedHashMap<Object, Object>> mappedMND = new HashMap<>();

            //더 많은거
            lastlySavedTemplate.forEach(template -> {
                lastlySavedId.add(template.getId());
                toDelete.add(template.getId());
            });
            //더 적거나 같은거
            modifiedNDeleted.forEach(template -> {
                Long templateId = Long.parseLong(template.get("id").toString());
                mappedMND.put(templateId, template);
                MNDId.add(templateId);
            });
            MNDId.forEach(temId -> {
                //남아 있으면 수정 대상, 남아있으면 삭제 대상에서 제외
                if (lastlySavedId.contains(temId)) {
                    toModify.add(temId);
                    toDelete.remove(toDelete.indexOf(temId));
                }
            });

            toDelete.forEach(toDeleteId -> {
                delete(toDeleteId);
            });
            toModify.forEach(toModifyId -> {
                LinkedHashMap<Object, Object> toModifyTemplate = mappedMND.get(toModifyId);
                if (checkIfModified(toModifyId, toModifyTemplate)) {
                    System.out.println("EDIT" + toModifyId);
                    TemplateEdit templateEdit = TemplateEdit.builder()
                            .gitID(toModifyTemplate.get("gitID").toString())
                            .templateName(toModifyTemplate.get("templateName").toString())
                            .templateNumber(Integer.parseInt(toModifyTemplate.get("templateNumber").toString()))
                            .template(toModifyTemplate.get("template").toString())
                            .hotKey(toModifyTemplate.get("hotKey").toString())
                            .build();
                    System.out.println(templateEdit);
                    edit(templateEdit, toModifyId);
                }
            });
        }

//        //Lastly saved template DB
//        List<Template> userTemplateList= templateRepository.findByGitID(gitID);
//        List<Integer> userTemplateNumberList = new ArrayList<>();
//        List<Integer> toRemoveNum = new ArrayList<>();
//        List<Integer> modifiedNDeletedNum = new ArrayList<>();
//        List<Integer> toEditNum = new ArrayList<>();
//        List<Long> toEditId = new ArrayList<>();
//        Map<Long, LinkedHashMap<Object, Object>> mappedMND = new HashMap<>();
//
//        //{1 (temNum): {template}, ...}
//        modifiedNDeleted.forEach(template -> {
//            mappedMND.put(Long.parseLong(template.get("id").toString()), template);
//        });
//
//        //userTemplateNumberList == modifiedNDeletedNum + toRemove
//
//        //userTemplateNumberList는 마지막 template 리스트
//        userTemplateList.forEach(userTemplate -> {
//            userTemplateNumberList.add(userTemplate.getTemplateNumber());
//            toRemoveNum.add(userTemplate.getTemplateNumber());
//        });
//        modifiedNDeleted.forEach(template -> {
//            modifiedNDeletedNum.add(Integer.parseInt(template.get("templateNumber").toString()));
//        });
//
//        modifiedNDeletedNum.forEach(templateNum -> {
//            //DELETE
//            if (userTemplateNumberList.contains(templateNum)) {
//                toRemoveNum.remove(toRemoveNum.indexOf(templateNum));
//                toEditNum.add(templateNum);
//            }
////            //EDIT
////            else {
////                System.out.println("EDIT" + templateNum);
////                LinkedHashMap<Object, Object> toEdit = mappedMND.get(templateNum);
////                TemplateEdit templateEdit = TemplateEdit.builder()
////                        .gitID(toEdit.get("gitID").toString())
////                        .templateName(toEdit.get("templateName").toString())
////                        .templateNumber(Integer.parseInt(toEdit.get("templateNumber").toString()))
////                        .template(toEdit.get("template").toString())
////                        .hotKey(toEdit.get("hotKey").toString())
////                        .build();
////                edit(templateEdit, gitID, Long.parseLong(toEdit.get("id").toString()));
////            }
//        });
//        toRemoveNum.forEach(removeTemplateNum -> {
//            Template toRemove = templateRepository.findByGitIDAndTemplateNumber(gitID, removeTemplateNum).orElseThrow();
//            templateRepository.delete(toRemove);
//        });
    }

    public boolean checkIfModified(Long toModifyId, LinkedHashMap<Object, Object> toModifyTemplate) {
        Boolean ifModified = false;
        Template originalTemplate = templateRepository.findById(toModifyId).orElseThrow();

        if (!toModifyTemplate.get("templateName").equals(originalTemplate.getTemplateName())) {
            ifModified = true;
        }
        if (!toModifyTemplate.get("templateNumber").equals(originalTemplate.getTemplateNumber().toString())) {
            ifModified = true;
        }
        if (!toModifyTemplate.get("template").equals(originalTemplate.getTemplate())) {
            ifModified = true;
        }
        if (!toModifyTemplate.get("hotKey").equals(originalTemplate.getHotKey())) {
            ifModified = true;
        }
        return ifModified;
    }


    public boolean IntAble(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<LinkedHashMap<Object, Object>> trimWholeTemplate(List<LinkedHashMap<Object, Object>> wholeTemplate) {
        wholeTemplate.forEach(untrimmed -> {
            untrimmed.forEach((key, value)-> {
                if (key.toString().equals("templateNumber")) {
                    return;
                }
                else {
                    value.toString().trim();
                }

            });
        });
        return wholeTemplate;
    }
}
