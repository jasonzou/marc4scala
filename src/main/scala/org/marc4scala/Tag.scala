/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * Tag.scala is part of marc4scala.
 *
 * marc4scala is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * marc4scala is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with marc4scala; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.marc4scala

/**
  * Created by jason on 3/1/16.
  */
class Tag {
        /// <summary>
        /// This class is for MARC tags.
        /// </summary>
        
        private char[] buff = new char[3] { '0', '0', '0' };
        public string _warn;

        public MARCTag(string str)
        {
            bool test;

            test = Regex.IsMatch(str, "^[0-9A-Z]{3}$");
            test |= Regex.IsMatch(str, "^[0-9a-z]{3}$");
            if (test)
            {
                str.CopyTo(0, buff, 0, 3);
            }
            else
            {
                _warn = "Invalid Tagno";
            }
        }

        public string tagStr
        {
            get
            {
                string temp;
                temp = new String(buff);
                return temp;
            }

        }

        public bool isControl()
        {
            if (_warn != null)
            {
                return false;
            }
            else
            {
                if (buff[0] == '0' && buff[1] == '0')
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }

        public bool isData()
        {
            if (buff[0] == '0' || buff[1] == '0')
            {
                return false;
            }
            else
            {
                return true;
            }
        }
}
