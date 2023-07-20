package com.example.radius.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("errors")
	val errors: Any? = null
)

data class DataItem(

	@field:SerializedName("comment_count")
	val commentCount: Int? = null,

	@field:SerializedName("uploaded_by")
	val uploadedBy: UploadedBy? = null,

	@field:SerializedName("file")
	val file: String? = null,

	@field:SerializedName("uploaded_at")
	val uploadedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("section")
	val section: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("version")
	val version: Int? = null,

	@field:SerializedName("file_size")
	val fileSize: Any? = null,

	@field:SerializedName("tags")
	val tags: List<Any?>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class UploadedBy(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("organization_name")
	val organizationName: String? = null
)

data class TagsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
