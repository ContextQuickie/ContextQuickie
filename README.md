# ContextQuickie
An eclipse add-on which extends the context menu for accessing various windows tools like Beyond Compare, Tortoise SVN or Tortoise Git.
Important note: The update site URL has changed:
* Releases: http://contextquickie.github.io/ContextQuickie/Releases
* Development: http://contextquickie.github.io/ContextQuickie/Development

The wiki provides further information:
* [Installation](https://github.com/ContextQuickie/ContextQuickie/wiki/Installation)
* [Usage](https://github.com/ContextQuickie/ContextQuickie/wiki/Usage)

# ContextQuickie.TeamProvider
This plugin provides a Tortiose SVN based team provider to eclise which forwards the following eclipse operations to the working copy:
* Adding new files
* Copying files
* Moving files

To use ContextQuickie.Teamprovider one of the following Tortoise SVN versions must be installed:
* 1.9.7/1.9.8
* 1.10.1/1.10.2/1.10.3
* 1.11.0/1.11.1

Remarks:
* Torotise SVN versions 1.8.x and below cannot be used because the JavaHL libraries of this versions don't provide the required APIs.
* If you're using a 64bit version of eclipse you also need the 64bit version of Tortoise SVN. Same for the 32bit version. Mixing architecutres doesn't work.

# Screenshots
Compare two files selected in a navigator view with Beyond Compare:

![Compare two files selected in a navigator view with Beyond Compare](https://github.com/ContextQuickie/ContextQuickie/blob/master/Images/CompareTwoFiles.png)

Tortoise Git and Beyond Compare in a navigator view:

![Tortoise Git and Beyond Compare in a navigator view](https://github.com/ContextQuickie/ContextQuickie/blob/master/Images/TortoiseGitAndBeyondCompare.png)

Tortoise SVN and Beyond Compare in a navigator view:

![Tortoise SVN and Beyond Compare in a navigator view](https://github.com/ContextQuickie/ContextQuickie/blob/master/Images/TortoiseSvnAndBeyondCompare.png)

Tortoise SVN and Beyond Compare in an editor view:

![Tortoise SVN and Beyond Compare in an editor view](https://github.com/ContextQuickie/ContextQuickie/blob/master/Images/TortoiseSvnAndBeyondCompareInEditor.png)
