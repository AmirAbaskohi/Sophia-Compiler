.class Cart
.super java/lang/Object
.field orders LList;
.method public <init>()V
.limit stack 128
.limit locals 128
		aload 0
		invokespecial java/lang/Object/<init>()V
		aload 0
		new List
		dup
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		dup
		new List
		dup
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		dup
		ldc ""
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		new List
		dup
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		dup
		ldc ""
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		new List
		dup
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		dup
		ldc ""
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		new List
		dup
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		dup
		ldc ""
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		putfield Cart/orders LList;
		return
.end method
.method public addToCart(LList;Ljava/lang/Integer;)V
.limit stack 128
.limit locals 128
		aload 0
		getfield Cart/orders LList;
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		new List
		dup
		aload 1
		invokespecial List/<init>(LList;)V
		invokevirtual List/setElement(ILjava/lang/Object;)V
		aload 0
		getfield Cart/orders LList;
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast List
		pop
		return
.end method
.method public getSum()Ljava/lang/Integer;
.limit stack 128
.limit locals 128
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 1
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 2
		new List
		dup
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		dup
		ldc ""
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		dup
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		astore 3
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 1
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		pop
		aload 0
		getfield Cart/orders LList;
		ldc 0
		istore 4
	Label_0:
		iload 4
		ldc 4
		if_icmpge Label_1
		dup
		iload 4
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast List
		astore 3
		aload 3
		ldc 2
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		ldc 0
		if_icmpne Label_5
		ldc 1
		goto Label_6
		Label_5:
		ldc 0
		Label_6:
		ifeq Label_3
		goto Label_1
		goto Label_4
	Label_3:
	Label_4:
		aload 3
		ldc 1
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		aload 3
		ldc 2
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		imul
		aload 3
		ldc 2
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		ldc 100
		imul
		ldc 1000
		irem
		iadd
		aload 3
		ldc 1
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		ldc 100
		idiv
		isub
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 2
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		pop
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		iadd
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 1
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		pop
	Label_2:
		iload 4
		ldc 1
		iadd
		istore 4
		goto Label_0
	Label_1:
		pop
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		areturn
.end method
