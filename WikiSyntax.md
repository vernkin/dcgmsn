由于Google在本页面编辑Wiki不提供可视化编辑

# 参考网站 #
http://developer.mozilla.org/cn/docs/MDC:Wiki_Markup_Reference

http://code.google.com/p/support/wiki/WikiSyntax

# 部分常用示例 #

  * 以下内容全部来自[Google Wiki Syntax](http://code.google.com/p/support/wiki/WikiSyntax),版权归原作者[jrobbins](http://code.google.com/u/jrobbins/)所有。

  * 因为从网页上只能看到html版本，以下翻译成wiki格式（选择编辑本页面即可）


## Paragraphs ##
Use one or more blank lines to separate paragraphs.

## Typeface ##
|Name/Sample | **Markup** |
|:-----------|:-----------|
| _italic_ | `_italic_`  |
| **bold** | `*bold*`  |
| `code` | ```code```  |
| `code` | `{{{code`}}} |
| <sup>super</sup>script | `^super^script` |
| <sub>sub</sub>script | `,,sub,,script`  |
| ~~strikeout~~ | `~~strikeout~~`  |

You can mix these typefaces in some ways:

| **Markup** | **Result** |
|:-----------|:-----------|
| `_*bold* in italics_` | _**bold** in italics_ |
| `*_italics_ in bold*` | **_italics_ in bold** |
| `*~~strike~~ works too*` | **~~strike~~ works too** |
| `~~as well as _this_ way round~~`  | ~~as well as _this_ way round~~ |

## Code ##
If you have a multiline code block that you want to display verbatim, use the multiline code delimiter:
```
  {{{
  def fib(n):  
    if n == 0 or n == 1:    
      return n  
    else:   
    # This recursion is not good for large numbers.    
      return fib(n-1) + fib(n-2)
   }}}
```
Which results in:

```
  def fib(n):  
    if n == 0 or n == 1:    
      return n  
    else:   
    # This recursion is not good for large numbers.    
      return fib(n-1) + fib(n-2)
```

## Headings ##
```
  = Heading =
  == Subheading ==
  === Level 3 ===
  ==== Level 4 ====
  ===== Level 5 =====
  ====== Level 6 ======
```
Which results in:

> # Heading #
> ## Subheading ##
> ### Level 3 ###
> #### Level 4 ####
> ##### Level 5 #####
> ###### Level 6 ######


## Dividers ##
Four or more dashes on a line by themselves results in a horizontal rule.


## Lists ##
Google Code wikis support both bulleted and numbered lists. A list must be indented at least one space to be recognized as such. You can also nest lists one within the other by appropriate use of indenting:

```
  The following is:  
    * A list  
    * Of bulleted items    
       # This is a numbered sublist    
       # Which is done by indenting further  
    * And back to the main bulleted list 
   * This is also a list 
   * With a single leading space 
   * Notice that it is rendered  
       # At the same levels  
       # As the above lists. 
    * Despite the different indentation levels.
```

The following is:
  * A list
  * Of bulleted items
    1. This is a numbered sublist
    1. Which is done by indenting further
  * And back to the main bulleted list
  * This is also a list
  * With a single leading space
  * Notice that it is rendered
    1. At the same levels
    1. As the above lists.
  * Despite the different indentation levels.

## Quoting ##
Block quotes place emphasis on a particular extract of text in your page. Block quotes are created by indenting a paragraph by at least one space:

```
   Someone once said: 
     This sentence will be quoted in the future as the canonical example 
     of a quote that is so important that it should be visually separate
     from the rest of the text in which it appears.
```

> Someone once said:
> > This sentence will be quoted in the future as the canonical example
> > of a quote that is so important that it should be visually separate
> > from the rest of the text in which it appears.


## Links ##
Links are central to the wiki principle, as they create the web of content. Google Code wiki permits both internal (within the wiki) and external links, and in some cases automatically creates a link when it recognizes either a WikiWord or an URL.


## Internal wiki links ##
Internal links within a wiki are created using the syntax below. If you add a wiki link to a page that does not exist, the link will appear with a LittleLink? to all project members and owners. Clicking that link will take you to the page creation form where you can enter content for that page.

If you are not logged in, wiki links that point to non-existent pages will appear as plain text, without the link to the page creation form. When you create the target page as a project member or owner, the link will switch to being an actual hyperlink for all viewers of the page.

```
  WikiSyntax is identified and linked automaticallyWikipage is not identified, so if you 
  have a page named [Wikipage] youneed to link it explicitly.If the WikiSyntax page is
  actually about reindeers, you can provide adescription, so that people know you're
  actually linking to a page on[WikiSyntax reindeer flotillas].If you want to mention!
  WikiSyntax without it being autolinked, use anexclamation mark to prevent linking.
```

WikiSyntax is identified and linked automaticallyWikipage is not identified, so if you

have a page named [Wikipage](Wikipage.md) youneed to link it explicitly.If the WikiSyntax page is

actually about reindeers, you can provide adescription, so that people know you're

actually linking to a page on[reindeer flotillas](WikiSyntax.md).If you want to mention!

WikiSyntax without it being autolinked, use anexclamation mark to prevent linking.


## Links to external pages ##
You can of course link to external pages from your own wiki pages, using a syntax similar to that for internal links:

```
  Plain URLs such as http://www.google.com/ or ftp://ftp.kernel.org/ areautomatically
  made into links.You can also provide some descriptive text. For example, the
  followinglink points to the [http://www.google.com Google home page].If your link
  points to an image, it will get inserted as an image taginto the
  page:http://code.google.com/images/code_sm.png You can also make the image into a link,
  by setting the image URL asthe description of the URL you want to link:
  [http://code.google.com/ http://code.google.com/images/code_sm.png]
```

Plain URLs such as http://www.google.com/ or [ftp://ftp.kernel.org/](ftp://ftp.kernel.org/) areautomatically

made into links.You can also provide some descriptive text. For example, the

followinglink points to the [Google home page](http://www.google.com).If your link

points to an image, it will get inserted as an image taginto the

page:![http://code.google.com/images/code_sm.png](http://code.google.com/images/code_sm.png) You can also make the image into a link,

by setting the image URL asthe description of the URL you want to link:

[![](http://code.google.com/images/code_sm.png)](http://code.google.com/)


## Tables ##
Tables are created by entering the content of each cell separated by || delimiters. You can insert other inline wiki syntax in table cells, including typeface formatting and links.

```
  || *Year* || *Temperature (low)* || *Temperature (high)* ||
  || 1900 || -10 || 25 ||
  || 1910 || -15 || 30 ||
  || 1920 || -10 || 32 ||
  || 1930 || _N/A_ || _N/A_ ||
  || 1940 || -2 || 40 ||
```

| **Year** | **Temperature (low)** | **Temperature (high)** |
|:---------|:----------------------|:-----------------------|
| 1900 | -10 | 25 |
| 1910 | -15 | 30 |
| 1920 | -10 | 32 |
| 1930 | _N/A_ | _N/A_ |
| 1940 | -2 | 40 |