public class Plan1571773420188 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
} else {
if ( StartServer("A") ) {
StartServer("B");
} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

}

}

}
}
