package com.jit.rec.recipetoria.swagger;

import com.jit.rec.recipetoria.dto.TagDTO;
import com.jit.rec.recipetoria.entity.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface TagControllerInterface {
    @Operation(summary = "Get a list of all tags of current ApplicationUser",
            description = """
                    send:\n
                    response is array of objects "TagDTO":\n
                        "createdTagDTO": \n
                                   "id": 4, \n
                                   "name": "third tag",\n
                                   "icon": null, -- url of a pic\n
                                   "applicationUserId": 1, -- owner of a tag\n
                                   "recipeIds": [] -- a list of recipe ids are tagged by this tag\n
                    """)
    ResponseEntity<ApiResponse> getAllTags();

    @Operation(summary = "Get a tag by id",
            description = """
                    send:\n
                         "id":"Long"\n
                    response is an object "TagDTO":\n
                        "createdTagDTO": \n
                                   "id": 4, \n
                                   "name": "third tag",\n
                                   "icon": null, -- url of a pic\n
                                   "applicationUserId": 1, -- owner of a tag\n
                                   "recipeIds": [] -- a list of recipe ids are tagged by this tag\n
                    """)
    ResponseEntity<ApiResponse> getTagById(Long tagId);

    @Operation(summary = "Create a new Tag",
            description = """
                    send:{\n
                          "name": "string",\n
                          "icon": "string"\n
                          }\n
                    response is a created object"TagDTO":\n
                        createdTagDTO": {\n
                             "id": 4,  \n
                             "name": "tag3",\n
                             "icon": null,  -- url of a pic\n
                             "applicationUserId": 1, -- owner of a tag\n
                             "recipeIds": []  -- a list of recipe ids are tagged by this tag\n
                              }\n
                                        """)
    ResponseEntity<ApiResponse> createTag(TagDTO newTagDTO);

    @Operation(summary = "Update a Tag by id",
            description = """
                    send:\n
                          "id": "Long", --REQUIRED\n
                          "TagDTO" --new info for a tag name is REQUIRED \n
                                "name": "updated name",\n
                                "icon": updated url,  -- url of a pic\n
                          \n
                    response: updated object "TagDTO" with full info\n
                        Updated TagDTO: \n
                             "id": 4,  --same id \n
                             "name": "updated name",\n
                             "icon": updated url,  -- url of a pic\n
                             "applicationUserId": 1, -- owner of a tag\n
                             "recipeIds": []  -- a list of recipe ids are tagged by this tag\n
                                        """)
    ResponseEntity<ApiResponse> updateTagById(Long tagId,
                                              TagDTO updatedTag);

    @Operation(summary = "Delete a tag by id",
            description = """
                    send:{\n
                        "id":"Long"\n
                    response: status 200 (OK)\n
                    """)
    ResponseEntity<ApiResponse> deleteTagById(Long tagId);

}
