package com.moon.bookstore.converter.output;

import com.moon.bookstore.api.entity.BookCategory;
import com.moon.bookstore.api.vo.BookCategoryVo;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-14T17:24:22+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class BookCategoryOutputConverterImpl implements BookCategoryOutputConverter {

    @Override
    public BookCategoryVo entity2Vo(BookCategory bookCategory) {
        if ( bookCategory == null ) {
            return null;
        }

        BookCategoryVo bookCategoryVo = new BookCategoryVo();

        bookCategoryVo.setId( bookCategory.getId() );
        bookCategoryVo.setCategoryName( bookCategory.getCategoryName() );
        bookCategoryVo.setCategoryIcon( bookCategory.getCategoryIcon() );

        return bookCategoryVo;
    }
}
