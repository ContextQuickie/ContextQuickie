#include "stdafx.h"
#include "contextquickie_handlers_tortoise_git_Translation.h"

#include "Windows.h"
#include <atlstr.h>

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

  if (!lpBuffer)
    return 0;
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

JNIEXPORT jstring JNICALL Java_contextquickie_handlers_tortoise_git_Translation_getTranslatedString(JNIEnv* env, jobject, jstring library, jlong languageId, jlong menuId, jstring defaultValue)
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