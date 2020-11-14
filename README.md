# ContextQuickie
An eclipse add-on which extends the context menu for accessing various windows tools like Beyond Compare, TortoiseSVN or TortoiseGit.
Important note: The update site URL has changed:
* Releases: http://contextquickie.github.io/ContextQuickie/Releases
* Development: http://contextquickie.github.io/ContextQuickie/Development

The wiki provides further information:
* [Installation](https://github.com/ContextQuickie/ContextQuickie/wiki/Installation)
* [Usage](https://github.com/ContextQuickie/ContextQuickie/wiki/Usage)

# ContextQuickie.TeamProvider
This plugin provides a TortoiseSVN based team provider to eclipse which forwards the following eclipse operations to the working copy:
* Adding new files
* Copying files
* Moving files

Starting with version 0.4.0, it is possible to export and import projects sets which can be e.g. used for automatic deployment with Eclipse Oomph. A detailed description of the project set file format can be found [here](https://github.com/ContextQuickie/ContextQuickie/wiki/Team-Project-Set-File-Format).

To use ContextQuickie.Teamprovider one of the following TortoiseSVN versions must be installed:
* 1.9.7/1.9.8
* 1.10.1/1.10.2/1.10.3
* 1.11.0/1.11.1

Remarks:
* TortoiseSVN versions 1.8.x and below cannot be used because the JavaHL libraries of this versions don't provide the required APIs.
* If you're using a 64bit version of eclipse you also need the 64bit version of TortoiseSVN. Same for the 32bit version. Mixing architectures doesn't work.

# Screenshots
Compare two files selected in a navigator view with Beyond Compare:

![Compare two files selected in a navigator view with Beyond Compare](https://github.com/ContextQuickie/ContextQuickie/blob/master/Images/CompareTwoFiles.png)

TortoiseGit and Beyond Compare in a navigator view:

![TortoiseGit and Beyond Compare in a navigator view](https://github.com/ContextQuickie/ContextQuickie/blob/master/Images/TortoiseGitAndBeyondCompare.png)

TortoiseSVN and Beyond Compare in a navigator view:

![TortoiseSVN and Beyond Compare in a navigator view](https://github.com/ContextQuickie/ContextQuickie/blob/master/Images/TortoiseSvnAndBeyondCompare.png)

TortoiseSVN and Beyond Compare in an editor view:

![TortoiseSVN and Beyond Compare in an editor view](https://github.com/ContextQuickie/ContextQuickie/blob/master/Images/TortoiseSvnAndBeyondCompareInEditor.png)
