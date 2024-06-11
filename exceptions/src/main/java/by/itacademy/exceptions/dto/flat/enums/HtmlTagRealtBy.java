package by.itacademy.exceptions.dto.flat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.imageio.metadata.IIOMetadataNode;

@AllArgsConstructor
@Getter
public enum HtmlTagRealtBy {
    DIV("div"),
    SECTION("section"),
    SPAN("span"),
    P("p"),
    A("a"),
    H2("h2"),
    IMG("img"),
    SRC("src"),
    LI("li"),
    A_HREF("a[href]"),
    HREF("href"),
    RELATIVE_LI("li.relative"),
    DESCRIPTION_DIV("section.bg-white div.description_wrapper__tlUQE p"),
    DIV_CLASS("div.sm\\:w-full.w-full.p-1\\.5.sm\\:p-2\\.5"),
    NOTE_DIV("div.text-basic-900 div");

    private final String tagName;

}
