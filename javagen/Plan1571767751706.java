public class Plan1571767751706 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

if ( StartServer("A") ) {
StartServer("A");
} else {
if ( StartServer("A") ) {
StartServer("A");
} else {
StartServer("A");
}

}


StartServer("B");

}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



}
}
