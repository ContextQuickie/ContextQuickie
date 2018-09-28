// ContextQuickie - An eclipse add-on which extens the context menu for accessing various tools.

// Copyright (C) 2009-2014, 2016 - TortoiseGit
// Copyright (C) 2003-2006, 2008 - TortoiseSVN

// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.

// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software Foundation,
// 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
//
#include "stdafx.h"
#include "contextquickie_tortoise_Translation.h"

#include "Windows.h"
#include <atlstr.h>

// GPL Software usage: The following section was copied from Tortoise Git 2.7.0.0 UniCodeUtils.cpp
// and adapted for the usage in ContextQuickie.
#pragma warning(push)
#pragma warning(disable: 4200)
struct STRINGRESOURCEIMAGE
{
  WORD nLength;
  WCHAR achString[];
};
#pragma warning(pop)	// C4200

int LoadStringEx(HINSTANCE hInstance, UINT uID, LPTSTR lpBuffer, int nBufferMax, WORD wLanguage)
{
  const STRINGRESOURCEIMAGE* pImage;
  const STRINGRESOURCEIMAGE* pImageEnd;
  ULONG nResourceSize;
  HGLOBAL hGlobal;
  UINT iIndex;
#ifndef UNICODE
  BOOL defaultCharUsed;
#endif
  int ret;

  // ContextQuickie: Disabled buffer null pointer check because buffer is alway provided  
  // if (!lpBuffer)
  //   return 0;

  lpBuffer[0] = L'\0';
  HRSRC hResource = FindResourceEx(hInstance, RT_STRING, MAKEINTRESOURCE(((uID >> 4) + 1)), wLanguage);
  if (!hResource)
  {
    //try the default language before giving up!
    hResource = FindResource(hInstance, MAKEINTRESOURCE(((uID >> 4) + 1)), RT_STRING);
    if (!hResource)
      return 0;
  }
  hGlobal = LoadResource(hInstance, hResource);
  if (!hGlobal)
    return 0;
  pImage = (const STRINGRESOURCEIMAGE*)::LockResource(hGlobal);
  if (!pImage)
    return 0;

  nResourceSize = ::SizeofResource(hInstance, hResource);
  pImageEnd = reinterpret_cast<const STRINGRESOURCEIMAGE*>(LPBYTE(pImage) + nResourceSize);
  iIndex = uID & 0x000f;

  while ((iIndex > 0) && (pImage < pImageEnd))
  {
    pImage = reinterpret_cast<const STRINGRESOURCEIMAGE*>(LPBYTE(pImage) + (sizeof(STRINGRESOURCEIMAGE) + (pImage->nLength * sizeof(WCHAR))));
    iIndex--;
  }
  if (pImage >= pImageEnd)
    return 0;
  if (pImage->nLength == 0)
    return 0;
#ifdef UNICODE
  ret = pImage->nLength;
  if (ret >= nBufferMax)
    ret = nBufferMax - 1;
  wcsncpy_s((wchar_t *)lpBuffer, nBufferMax, pImage->achString, ret);
  lpBuffer[ret] = L'\0';
#else
  ret = WideCharToMultiByte(CP_ACP, 0, pImage->achString, pImage->nLength, (LPSTR)lpBuffer, nBufferMax - 1, ".", &defaultCharUsed);
  lpBuffer[ret] = L'\0';
#endif
  return ret;
}
// End GPL Software usage

JNIEXPORT jstring JNICALL Java_contextquickie_tortoise_Translation_getTranslatedString(JNIEnv* env, jobject, jstring library, jlong languageId, jlong menuId, jstring defaultValue)
{
  jstring returnValue = defaultValue;
  const char* nativeLibrary = env->GetStringUTFChars(library, FALSE);
  HINSTANCE libraryInst = LoadLibrary(CA2W(nativeLibrary));
  if (libraryInst != NULL)
  {
    TCHAR buffer[255];
    if (LoadStringEx(libraryInst, (UINT)menuId, buffer, _countof(buffer), MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT)) != 0)
    {
      returnValue = env->NewString((jchar*)buffer, 255);
    }
  }

  env->ReleaseStringUTFChars(library, nativeLibrary);

  return returnValue;
}