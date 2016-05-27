/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.reactor.uaa.users;

import org.cloudfoundry.reactor.InteractionContext;
import org.cloudfoundry.reactor.TestRequest;
import org.cloudfoundry.reactor.TestResponse;
import org.cloudfoundry.reactor.uaa.AbstractUaaApiTest;
import org.cloudfoundry.uaa.users.Approval;
import org.cloudfoundry.uaa.users.Email;
import org.cloudfoundry.uaa.users.Group;
import org.cloudfoundry.uaa.users.ListUsersRequest;
import org.cloudfoundry.uaa.users.ListUsersResponse;
import org.cloudfoundry.uaa.users.Meta;
import org.cloudfoundry.uaa.users.Name;
import org.cloudfoundry.uaa.users.User;
import reactor.core.publisher.Mono;

import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.cloudfoundry.uaa.SortOrder.ASCENDING;
import static org.cloudfoundry.uaa.users.ApprovalStatus.APPROVED;
import static org.cloudfoundry.uaa.users.MembershipType.DIRECT;

public final class ReactorUsersTest {

    public static final class List extends AbstractUaaApiTest<ListUsersRequest, ListUsersResponse> {

        private final ReactorUsers users = new ReactorUsers(AUTHORIZATION_PROVIDER, HTTP_CLIENT, OBJECT_MAPPER, this.root);

        @Override
        protected InteractionContext getInteractionContext() {
            return InteractionContext.builder()
                .request(TestRequest.builder()
                    .method(GET).path(
                        "/Users?count=50&filter=id%2Beq%2B%22a94534d5-de08-41eb-8712-a51314e6a484%22%2Bor%2Bemail%2Beq%2B%22Da63pG@test.org%22&sortBy=email&sortOrder=ascending&startIndex=1")
                    .build())
                .response(TestResponse.builder()
                    .status(OK)
                    .payload("fixtures/uaa/users/GET_response.json")
                    .build())
                .build();
        }

        @Override
        protected ListUsersResponse getResponse() {
            return ListUsersResponse.builder()
                .resource(User.builder()
                    .id("a94534d5-de08-41eb-8712-a51314e6a484")
                    .externalId("test-user")
                    .meta(Meta.builder()
                        .version(0)
                        .created("2016-05-18T18:25:24.036Z")
                        .lastModified("2016-05-18T18:25:24.036Z")
                        .build())
                    .userName("Da63pG@test.org")
                    .name(Name.builder()
                        .familyName("family name")
                        .givenName("given name")
                        .build())
                    .email(Email.builder()
                        .value("Da63pG@test.org")
                        .primary(false)
                        .build())
                    .group(Group.builder()
                        .value("4622c5e1-ddfd-4e17-9e81-2ae3c03972be")
                        .display("password.write")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("62f67643-05d8-43c6-b193-4cd6ab9960cb")
                        .display("cloud_controller.write")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("c47bf470-f9c4-4eea-97e4-490ce7b8f6f7")
                        .display("uaa.user")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("8a6add1f-d3ee-400c-a263-c4197351b78e")
                        .display("approvals.me")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("e10424ed-ed80-45ac-848b-7f7e79b00c42")
                        .display("cloud_controller.read")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("ede11441-6ffe-4510-81f8-bb40626155f0")
                        .display("openid")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("7e3d4b06-0d6b-43a1-ac3a-5f1b2642262c")
                        .display("scim.me")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("3b481f3c-d9a7-4920-a687-72cb0381b671")
                        .display("cloud_controller_service_permissions.read")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("4480c647-4047-4c6a-877f-70f5f96e8c11")
                        .display("oauth.approvals")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("542bb178-1c04-4bb5-813a-5a038319ac1d")
                        .display("user_attributes")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("c4ac4653-2fdd-4901-a028-9c9866cb4e9c")
                        .display("scim.userids")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("74fde138-daf3-4e4d-bb52-93a6cb727030")
                        .display("profile")
                        .type(DIRECT)
                        .build())
                    .group(Group.builder()
                        .value("1b18551f-eead-4076-90dd-b464998f6ddd")
                        .display("roles")
                        .type(DIRECT)
                        .build())
                    .approval(Approval.builder()
                        .userId("a94534d5-de08-41eb-8712-a51314e6a484")
                        .clientId("client id")
                        .scope("scim.read")
                        .status(APPROVED)
                        .lastUpdatedAt("2016-05-18T18:25:24.047Z")
                        .expiresAt("2016-05-18T18:25:34.047Z")
                        .build())
                    .active(true)
                    .verified(true)
                    .origin("uaa")
                    .zoneId("uaa")
                    .passwordLastModified("2016-05-18T18:25:24.000Z")
                    .schema("urn:scim:schemas:core:1.0")
                    .build())
                .startIndex(1)
                .itemsPerPage(50)
                .totalResults(1)
                .schema("urn:scim:schemas:core:1.0")
                .build();
        }

        @Override
        protected ListUsersRequest getValidRequest() throws Exception {
            return ListUsersRequest.builder()
                .filter("id+eq+\"a94534d5-de08-41eb-8712-a51314e6a484\"+or+email+eq+\"Da63pG@test.org\"")
                .count(50)
                .startIndex(1)
                .sortBy("email")
                .sortOrder(ASCENDING)
                .build();
        }

        @Override
        protected Mono<ListUsersResponse> invoke(ListUsersRequest request) {
            return this.users.list(request);
        }

    }

}
